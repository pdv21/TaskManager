package com.example.taskmanager.data

data class categoryResponse(
    val id: Int,
    val name: String
)

data class categoryListResponse(
    val data: List<categoryResponse>
)

