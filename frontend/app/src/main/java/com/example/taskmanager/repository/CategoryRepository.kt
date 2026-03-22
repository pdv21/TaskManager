package com.example.taskmanager.repository

import com.example.taskmanager.data.categoryResponse
import com.example.taskmanager.remote.RetrofitClient

class CategoryRepository {
    suspend fun getCategory(): List<categoryResponse>{
        return RetrofitClient.categoryApi.getCategory().data
    }
}