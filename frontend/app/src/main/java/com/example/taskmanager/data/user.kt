package com.example.taskmanager.data

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.tooling.preview.*

data class UserResponse(
    val id: Int,
    val full_name: String,
    val email: String,
    val phone: String,
    val create_at: String,
    val latitude: Double,
    val longitude: Double,
    val department: String,
    val title: String
)


