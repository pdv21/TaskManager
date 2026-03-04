const pool = require('../config/db');

const createUser = async (full_name, email, password) => {
    const [result] = await pool.query(
        `
        INSERT INTO users (full_name, email, password_hash) VALUES (?, ?, ?)
        `,
        [full_name, email, password]
    )
    return result.insertId;
}

const getUserByEmail = async(email) => {
    const [rows] = await pool.query(
        `
        SELECT * FROM users WHERE email = ?
        `,
        [email]
    );
    return rows[0];
}

module.exports = {
    createUser,
    getUserByEmail
};