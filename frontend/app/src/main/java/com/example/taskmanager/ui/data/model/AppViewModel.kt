package com.example.taskmanager.ui.data.model

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.taskmanager.ui.data.UserUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()


}