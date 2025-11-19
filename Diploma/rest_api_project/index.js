const express = require('express'); 
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const cors = require('cors');
require('dotenv').config(); 

const app = express();
const port = 3000;

// Import routers
const productsRouter = require('./routes/products');
const authRouter = require('./routes/auth');
const ordersRouter = require('./routes/orders');
const setupSwagger = require('./config/swagger');

// Public CORS configuration
const publicCorsOptions = {
  origin: '*', 
  methods: 'GET,HEAD,PUT,PATCH,POST,DELETE,OPTIONS',
  allowedHeaders: 'Content-Type,Authorization',
};

// Private CORS configuration for localhost
const privateCorsOptions = {
  origin: 'http://localhost:3000',
  methods: 'GET,HEAD,PUT,PATCH,POST,DELETE,OPTIONS',
  allowedHeaders: 'Content-Type,Authorization',
};

// Middleware
app.use(cors(publicCorsOptions)); 
app.use(express.json()); 
app.use(express.urlencoded({ extended: true }));
app.use(bodyParser.json()); 

// Use private CORS for specific routes
app.use('/auth', cors(privateCorsOptions), authRouter);

// Handle preflight requests
app.options('*', cors(publicCorsOptions));

// Route definitions
app.use('/products', productsRouter);
app.use('/orders', ordersRouter);

// Swagger setup
setupSwagger(app);

// MongoDB connection
const uri = process.env.MONGODB_URI;

mongoose
  .connect(uri)
  .then(() => {
    console.log('Connected to MongoDB');
  })
  .catch((err) => {
    console.error('Error connecting to MongoDB:', err.message);
  });



// Sample route
app.get('/', (req, res) => {
  res.send('Hello, this is your REST API!');
});

// Start server
app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});
