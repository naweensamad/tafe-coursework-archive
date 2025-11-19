const express = require('express');
const jwt = require('jsonwebtoken');
const Employee = require('../models/Employee'); 
const router = express.Router();

// JWT Secret
const JWT_SECRET = process.env.JWT_SECRET || 'your_jwt_secret'; 

/**
 * @route   POST /auth/register
 * @desc    Register a new employee
 * @access  Public
 */
router.post('/register', async (req, res) => {
  const { username, password } = req.body;


  if (!username || !password) {
    return res.status(400).json({ message: 'Username and password are required.' });
  }

  try {
 
    const existingEmployee = await Employee.findOne({ username });
    if (existingEmployee) {
      return res.status(400).json({ message: 'Username already exists.' });
    }

   
    const newEmployee = new Employee({ username, password });
    await newEmployee.save();

    res.status(201).json({ message: 'Employee registered successfully.' });
  } catch (error) {
    console.error('Registration error:', error);
    res.status(500).json({ message: 'Error registering employee.', error: error.message });
  }
});

/**
 * @route   POST /auth/login
 * @desc    Log in an employee
 * @access  Public
 */
router.post('/login', async (req, res) => {
  const { username, password } = req.body;


  if (!username || !password) {
    return res.status(400).json({ message: 'Username and password are required.' });
  }

  try {
    
    const employee = await Employee.findOne({ username });
    if (!employee) {
      return res.status(401).json({ message: 'Invalid credentials.' });
    }


    const isPasswordValid = await employee.isPasswordValid(password);
    if (!isPasswordValid) {
      return res.status(401).json({ message: 'Invalid credentials.' });
    }

    const token = jwt.sign(
      { id: employee._id, username: employee.username },
      JWT_SECRET,
      { expiresIn: '1h' }
    );

    res.status(200).json({ token, message: 'Login successful.' });
  } catch (error) {
    console.error('Login error:', error);
    res.status(500).json({ message: 'Error logging in.', error: error.message });
  }
});

/**
 * @route   GET /auth/profile
 * @desc    Get the profile of the logged-in employee
 * @access  Private
 */
router.get('/profile', async (req, res) => {
  const token = req.headers.authorization?.split(' ')[1];

  if (!token) {
    return res.status(401).json({ message: 'No token provided.' });
  }

  try {
 
    const decoded = jwt.verify(token, JWT_SECRET);
    const employee = await Employee.findById(decoded.id);

    if (!employee) {
      return res.status(404).json({ message: 'Employee not found.' });
    }

    res.status(200).json({ id: employee._id, username: employee.username, role: employee.role });
  } catch (error) {
    console.error('Profile error:', error);
    res.status(401).json({ message: 'Invalid token.', error: error.message });
  }
});

module.exports = router;
