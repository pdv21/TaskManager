package com.example.taskmanager.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.data.categoryResponse
import com.example.taskmanager.repository.CategoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CategoryViewModel: ViewModel() {
    private val categoryReposity = CategoryRepository()

    private var _category = MutableStateFlow<List<categoryResponse>>(emptyList())
    val category: StateFlow<List<categoryResponse>> = _category

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _createSuccess = MutableStateFlow(false)
    val createSuccess: StateFlow<Boolean> = _createSuccess

    fun getCategory() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = categoryReposity.getCategory()
                _category.value = result
                println("DEBUG_CATEGORY: $result")
            } catch (e: Exception){
                e.printStackTrace()
                println("ERROR_CATEGORY: ${e.message}")
            }
        }
    }
}