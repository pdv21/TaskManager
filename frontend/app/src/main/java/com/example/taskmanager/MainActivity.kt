package com.example.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskmanager.ui.screens.CreateTaskScreen
import com.example.taskmanager.ui.screens.HomeScreen
import com.example.taskmanager.ui.screens.LoginScreen
import com.example.taskmanager.ui.screens.ProfileScreen
import com.example.taskmanager.ui.screens.TaskScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskManagerTheme {
                TaskManagerApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskManagerApp(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ){
        composable("login"){
            LoginScreen(navController)
        }
        composable("home"){
            HomeScreen(
                name = "PDV",
                navController = navController
            )
        }
        composable("calendar"){
            TaskScreen(navController)
        }
        composable("createTask") {
            CreateTaskScreen(navController)
        }
        composable("profile"){
            ProfileScreen(navController)
        }
    }
}