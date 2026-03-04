package com.example.taskmanager.ui.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.ui.data.remote.TokenManager
import com.example.taskmanager.ui.data.repository.AuthResponsitory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {
    private val repository = AuthResponsitory()

    private val _loginState = MutableStateFlow<Boolean?>(null)
    val loginState: StateFlow<Boolean?> = _loginState

    fun login(email: String, password: String){
        viewModelScope.launch{
            try {
                val result = repository.login(email, password)
                if(result != null){
                    TokenManager.saveToken(result.token)
                    _loginState.value = true
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _loginState.value = false
            }
        }
    }
    fun register(fullName: String, email: String, password: String) {
        viewModelScope.launch {
            val result = repository.register(fullName, email, password)

            if (result != null) {
                println("Registered ID: ${result.userId}")
            } else {
                println("Register failed")
            }
        }
    }
}