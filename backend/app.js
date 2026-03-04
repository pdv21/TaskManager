const express = require('express');
const cors = require('cors');
const morgan = require('morgan');

const userRoutes = require('./routes/user.route');
const authRoutes = require('./routes/auth.route');
const taskRoutes = require('./routes/task.route');

const app = express();

app.use(cors());
app.use(express.json());
app.use(morgan('dev'));

app.get('/', (req, res) => {
  res.json({ message: 'Welcome to the Task Management API' });
});

app.use('/', userRoutes);

app.use('/auth', authRoutes);

app.use('/tasks', taskRoutes);

module.exports = app;