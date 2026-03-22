package com.example.taskmanager.remote

import com.example.taskmanager.data.ApiResponse
import com.example.taskmanager.data.UserResponse
import retrofit2.http.GET

interface UserApi {
    @GET("me")
    suspend fun getUser(): ApiResponse<UserResponse>
}