const jwt = require('jsonwebtoken');


const JWT_SECRET = 'your_jwt_secret'; 

const authMiddleware = (req, res, next) => {
   
    const token = req.header('Authorization')?.split(' ')[1];
    if (!token) {
        return res.status(401).json({ message: 'Access denied. No token provided.' });
    }

    try {
        
        const decoded = jwt.verify(token, JWT_SECRET);
        req.user = decoded; 
        next(); 
    } catch (err) {
        
        if (err.name === 'TokenExpiredError') {
            return res.status(401).json({ message: 'Token expired. Please log in again.' });
        }
        res.status(400).json({ message: 'Invalid token' });
    }
};

module.exports = authMiddleware;
