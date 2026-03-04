package com.example.taskmanager.ui.data.repository

import android.R
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.tooling.preview.*
import com.example.taskmanager.data.LoginRequest
import com.example.taskmanager.data.LoginResponse
import com.example.taskmanager.data.RegisterRequest
import com.example.taskmanager.data.RegisterResponse
import com.example.taskmanager.ui.data.remote.RetrofitClient

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

    suspend fun register(fullName: String, email: String, password: String): RegisterResponse?{
        val response = RetrofitClient.authApi.register(
            RegisterRequest(fullName, email, password)
        )
        return if(response.isSuccessful){
            response.body()
        } else {
            null
        }
    }
}