package com.example.taskmanager.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.taskmanager.R
import com.example.taskmanager.ui.component.CustomizedButton
import com.example.taskmanager.ui.component.CustomizedTextField
import com.example.taskmanager.model.AuthViewModel

@Composable
fun LoginScreen(
    navController: NavController
){
    val viewModel: AuthViewModel = viewModel()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginState by viewModel.loginState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarLogin()
        CustomizedTextField(
            value = email,
            onChange = {email = it},
            label = R.string.username,
            modifier =  Modifier.padding(top = 100.dp)
        )
        CustomizedTextField(
            value = password,
            onChange = {password = it},
            label = R.string.password,
            modifier = Modifier.padding(top = 20.dp)
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ){
            Text(stringResource(R.string.already_account))
            TextButton(onClick = {navController.navigate("register")}) {
                Text(
                    text = stringResource(R.string.sign_up),
                    color = Color(0xFF6200EE), // Màu tím nổi bật
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        CustomizedButton(
            text = R.string.login,
            onclick = {
                viewModel.login(email, password)
            },
            modifier = Modifier.padding(top = 150.dp),
            modifier2 = Modifier.padding(horizontal = 50.dp)
        )

        LaunchedEffect(loginState) {
            if (loginState == true) {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }
        }
    }
}

@Composable
fun TopBarLogin(){
    Text(
        text = stringResource(R.string.app_name),
        style = MaterialTheme.typography.displaySmall,
        fontWeight = FontWeight.SemiBold
    )
}
