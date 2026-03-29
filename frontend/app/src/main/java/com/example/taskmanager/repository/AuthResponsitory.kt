package com.example.taskmanager.repository

import android.util.Log
import com.example.taskmanager.data.ForgotPassRequest
import com.example.taskmanager.data.LoginRequest
import com.example.taskmanager.data.LoginResponse
import com.example.taskmanager.data.RegisterRequest
import com.example.taskmanager.data.RegisterResponse
import com.example.taskmanager.remote.RetrofitClient
import com.example.taskmanager.ui.screens.ForgotPassScreen

class AuthResponsitory{
    suspend fun login(email: String, password: String): LoginResponse?{
        val response = RetrofitClient.authApi.login(
            LoginRequest(email, password)
        )

        return if (response.isSuccessful){
            response.body()
        } else {
            null
        }
    }

    suspend fun register(
        fullName: String, email: String, password: String, phone: String,
        latitude: Double, longitude: Double, department: String, title: String): RegisterResponse?{
        val response = RetrofitClient.authApi.register(
            RegisterRequest(fullName, email, password, phone, latitude, longitude, department, title)
        )

        Log.d("API", "Code: ${response.code()}")

        if (response.isSuccessful) {
            Log.d("API", "Success: ${response.body()}")
        } else {
            Log.e("API", "Error: ${response.errorBody()?.string()}")
        }

        return if(response.isSuccessful){
            response.body()
        } else {
            null
        }
    }
}