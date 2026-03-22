package com.example.taskmanager.remote

import com.example.taskmanager.data.LoginRequest
import com.example.taskmanager.data.LoginResponse
import com.example.taskmanager.data.RegisterRequest
import com.example.taskmanager.data.RegisterResponse

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<RegisterResponse>

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>
}