package com.example.taskmanager.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.data.UserResponse
import com.example.taskmanager.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    private val userReposiroty = UserRepository()

    private val _user = MutableStateFlow<UserResponse?>(null)
    val user: StateFlow<UserResponse?> = _user

    fun getUser(){
        viewModelScope.launch {
            try{
                val result = userReposiroty.getUser()
                Log.d("USER_DEBUG", result.toString())
                _user.value = result
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}