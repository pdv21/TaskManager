const auth = require('../services/auth.service');
const brcypt = require('bcryptjs');
const jwt = require('jsonwebtoken');

const register = async(req, res) => {
    try {
        const {full_name, email, password} = req.body;
        const saltRounds = 10;
        const hashedPassword = await brcypt.hash(password, saltRounds);

        const userId =  await auth.createUser(full_name, email, hashedPassword);
        res.status(201).json({ message: 'User registered successfully', userId });
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
}

const login = async(req, res) => {
    try {
        const {email, password} = req.body;
        const user = await auth.getUserByEmail(email);
        if(!user) {
            return res.status(400).json({ message: 'Invalid credentials' });
        }
        const isMatch = await brcypt.compare(password, user.password_hash);
        if(!isMatch) {
            return res.status(400).json({ message: 'Invalid credentials' });
        }

        const token = jwt.sign(
            { userId: user.id, email: user.email },
            process.env.JWT_SECRET,
            { expiresIn: process.env.JWT_EXPIRES_IN }
        );
        res.status(200).json({ token });
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
}

module.exports = {
    register,
    login
};