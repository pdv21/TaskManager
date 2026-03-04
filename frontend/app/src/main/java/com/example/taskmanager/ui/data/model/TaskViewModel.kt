package com.example.taskmanager.ui.data.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.data.CreateTaskRequest
import com.example.taskmanager.data.TaskResponse
import com.example.taskmanager.ui.data.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel: ViewModel() {
    private val _tasks = MutableStateFlow<List<TaskResponse>>(emptyList())
    val tasks: StateFlow<List<TaskResponse>> = _tasks

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _createSuccess = MutableStateFlow(false)
    val createSuccess: StateFlow<Boolean> = _createSuccess

    fun getTask() {
        viewModelScope.launch {
            _isLoading.value = true

            try {
                val response = RetrofitClient.taskApi.getTasks()

                if (response.isSuccessful) {
                    _tasks.value = response.body()?.data ?: emptyList()
                } else {
                    _error.value = "Error: ${response.code()}"
                }

            } catch (e: Exception) {
                _error.value = e.message
            }

            _isLoading.value = false
        }
    }

    fun createTask(token: String, request: CreateTaskRequest) {
        viewModelScope.launch {

            _isLoading.value = true
            _error.value = null
            _createSuccess.value = false

            try {
                val response = RetrofitClient.taskApi.createTask(
                    request,
                    "Bearer $token"
                )

                Log.d("API_DEBUG", "code = ${response.code()}")
                Log.d("API_DEBUG", "body = ${response.body()}")

                if (response.isSuccessful) {
                    _createSuccess.value = true

                    // Reload lại list sau khi tạo
                    getTask()

                } else {
                    _error.value = "Error: ${response.code()}"
                }

            } catch (e: Exception) {
                _error.value = e.message
            }

            _isLoading.value = false
        }
    }
}