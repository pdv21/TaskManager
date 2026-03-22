package com.example.taskmanager.repository

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.tooling.preview.*
import com.example.taskmanager.data.UserResponse
import com.example.taskmanager.remote.RetrofitClient

class UserRepository{
    suspend fun getUser(): UserResponse{
        return RetrofitClient.userApi.getUser().data
    }
}