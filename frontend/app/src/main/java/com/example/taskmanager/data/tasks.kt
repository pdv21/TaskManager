package com.example.taskmanager.data

data class TaskResponse(
    val name: String,
    val start_date: String,
    val end_date: String,
    val description: String,
    val status_name: String,
    val color: String,
    val category_name: String
)
data class CreateTaskRequest(
    val title: String,
    val description: String,
    val start_date: String,
    val end_date: String,
    val status_id: Int,
    val category_id: Int
)
data class CreateTaskResponse(
    val message: String,
    val TaskId: Int
)
data class UpdateTaskRequest(
    val title: String?,
    val description: String?,
    val status: String?,
    val dueDate: String?
)
data class TaskListResponse(
    val data: List<TaskResponse>
)