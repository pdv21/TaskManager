package com.example.taskmanager.data

data class RegisterRequest(
    val full_name: String,
    val email: String,
    val password: String

)

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterResponse(
    val message: String,
    val userId: Int
)

data class LoginResponse(
    val token: String
)

data class ForgotPassRequest(
    val email: String
)