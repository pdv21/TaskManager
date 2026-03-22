package com.example.taskmanager.remote

import com.example.taskmanager.data.categoryListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface CategoryApi {
    @GET("categories")
    suspend fun getCategory(): categoryListResponse
}