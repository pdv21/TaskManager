package com.example.taskmanager.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.data.ForgotPassRequest
import com.example.taskmanager.remote.RetrofitClient
import com.example.taskmanager.remote.TokenManager
import com.example.taskmanager.repository.AuthResponsitory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {
    private val repository = AuthResponsitory()

    private val _loginState = MutableStateFlow<Boolean?>(null)
    val loginState: StateFlow<Boolean?> = _loginState

    private val _forgotState = MutableStateFlow<Boolean?>(null)
    val forgotState: StateFlow<Boolean?> = _forgotState

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
    fun register(
        fullName: String, email: String, password: String, phone: String,
        latitude: Double, longitude: Double, department: String, title: String) {
        viewModelScope.launch {
            try {
                val result = repository.register(
                    fullName,
                    email,
                    password,
                    phone,
                    latitude,
                    longitude,
                    department,
                    title
                )

                if (result != null) {
                    println("Registered ID: ${result.userId}")
                }
            } catch(e: Exception){
                Log.e("REGISTER","Error: ${e.message}", e)
            }
        }
    }
    fun forgotPassword(email: String){
        viewModelScope.launch {
            try{
                RetrofitClient.authApi.forgotPassword(ForgotPassRequest(email))
                _forgotState.value = true
            } catch(e: Exception){
                _forgotState.value = false
            }
        }
    }
}