package com.example.taskmanager.remote

import com.example.taskmanager.data.CreateTaskRequest
import com.example.taskmanager.data.CreateTaskResponse
import com.example.taskmanager.data.TaskListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface TaskApi{
    @GET("tasks")
    suspend fun getTasks(): Response<TaskListResponse>

    @POST("tasks/create")
    suspend fun createTask(
        @Body request: CreateTaskRequest,
        @Header("Authorization") token: String
    ): Response<CreateTaskResponse>
}