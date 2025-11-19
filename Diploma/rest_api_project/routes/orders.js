const express = require('express');
const router = express.Router();
const Order = require('../models/Order');
const authMiddleware = require('../middleware/authMiddleware');
const cors = require('cors');


const corsOptions = {
  origin: '*', 
  methods: 'GET,HEAD,PUT,PATCH,POST,DELETE,OPTIONS',
  allowedHeaders: 'Content-Type,Authorization',
};


router.options('*', cors(corsOptions));

/**
 * @swagger
 * tags:
 *   name: Orders
 *   description: API for managing orders
 */

// 1. **Retrieve All Orders**
/**
 * @route   GET /orders
 * @desc    Get all orders
 * @access  Protected
 */
router.get('/', cors(corsOptions), authMiddleware, async (req, res) => {
  try {
    const orders = await Order.find();
    res.status(200).json(orders);
  } catch (err) {
    res.status(500).json({ message: 'Error retrieving orders', error: err.message });
  }
});

// 2. **Retrieve a Single Order by ID**
/**
 * @route   GET /orders/:id
 * @desc    Get a single order by ID
 * @access  Protected
 */
router.get('/:id', cors(corsOptions), authMiddleware, async (req, res) => {
  try {
    const order = await Order.findById(req.params.id);
    if (!order) return res.status(404).json({ message: 'Order not found' });
    res.status(200).json(order);
  } catch (err) {
    res.status(500).json({ message: 'Error retrieving order', error: err.message });
  }
});

// 3. **Create a New Order**
/**
 * @route   POST /orders
 * @desc    Create a new order
 * @access  Protected
 */
router.post('/', cors(corsOptions), authMiddleware, async (req, res) => {
  const order = new Order(req.body);
  try {
    const newOrder = await order.save();
    res.status(201).json(newOrder);
  } catch (err) {
    res.status(400).json({ message: 'Error creating order', error: err.message });
  }
});

// 4. **Update an Existing Order**
/**
 * @route   PUT /orders/:id
 * @desc    Update an existing order
 * @access  Protected
 */
router.put('/:id', cors(corsOptions), authMiddleware, async (req, res) => {
  try {
    const updatedOrder = await Order.findByIdAndUpdate(req.params.id, req.body, { new: true });
    if (!updatedOrder) return res.status(404).json({ message: 'Order not found' });
    res.status(200).json(updatedOrder);
  } catch (err) {
    res.status(400).json({ message: 'Error updating order', error: err.message });
  }
});

// 5. **Partially Update an Order (PATCH)**
/**
 * @route   PATCH /orders/:id
 * @desc    Partially update an existing order
 * @access  Protected
 */
router.patch('/:id', cors(corsOptions), authMiddleware, async (req, res) => {
  try {
    const updatedOrder = await Order.findByIdAndUpdate(req.params.id, { $set: req.body }, { new: true });
    if (!updatedOrder) return res.status(404).json({ message: 'Order not found' });
    res.status(200).json(updatedOrder);
  } catch (err) {
    res.status(400).json({ message: 'Error updating order', error: err.message });
  }
});

// 6. **Delete an Order**
/**
 * @route   DELETE /orders/:id
 * @desc    Delete an existing order
 * @access  Protected
 */
router.delete('/:id', cors(corsOptions), authMiddleware, async (req, res) => {
  try {
    const deletedOrder = await Order.findByIdAndDelete(req.params.id);
    if (!deletedOrder) return res.status(404).json({ message: 'Order not found' });
    res.status(200).json({ message: 'Order deleted successfully' });
  } catch (err) {
    res.status(500).json({ message: 'Error deleting order', error: err.message });
  }
});

module.exports = router;
