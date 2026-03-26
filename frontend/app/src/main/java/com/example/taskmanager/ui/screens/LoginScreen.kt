package com.example.taskmanager.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.taskmanager.R
import com.example.taskmanager.buttonGradient
import com.example.taskmanager.buttonGradientEnd
import com.example.taskmanager.ui.component.CustomizedButton
import com.example.taskmanager.ui.component.CustomizedTextField
import com.example.taskmanager.model.AuthViewModel
import com.example.taskmanager.ui.component.PasswordTextField
import com.example.taskmanager.ui.component.RegisterTextField

@Composable
fun LoginScreen(
    navController: NavController
){
    val viewModel: AuthViewModel = viewModel()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginState by viewModel.loginState.collectAsState()

    LaunchedEffect(loginState) {
        if (loginState == true) {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box() {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
                    .background(brush = Brush.verticalGradient(buttonGradient)),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier.padding(top = 50.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Icon(
                        painter = painterResource(R.drawable.lock),
                        contentDescription = null,
                        tint = buttonGradientEnd,
                        modifier = Modifier
                            .padding(top = 70.dp)
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .padding(12.dp)
                    )

                    Text(
                        stringResource(R.string.welcome_back),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(top = 20.dp)
                    )

                    Text(
                        stringResource(R.string.sign_in_title),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Light,
                        color = Color.White
                    )
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .align(Alignment.Center)
                    .padding(top = 350.dp),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ){
                Column(
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    RegisterTextField(
                        R.string.enter_email,
                        R.string.email,
                        R.drawable.mail,
                        value = email,
                        onChange = {email = it}
                    )

                    PasswordTextField(
                        R.string.enter_pass,
                        R.string.password,
                        R.drawable.lock,
                        value = password,
                        onChange = {password = it}
                    )

                    TextButton(
                        onClick = {
                            navController.navigate("forgotPassword")
                        },
                        modifier = Modifier.padding(start = 180.dp)
                    ){
                        Text(
                            text = stringResource(R.string.forgot_pass),
                            color = Color(0xFF6200EE),
                            fontWeight = FontWeight.SemiBold,
                        )
                    }

                    Row(
                        modifier = Modifier.padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(stringResource(R.string.dont_have_account))

                        TextButton(onClick = {navController.navigate("register")}) {
                            Text(
                                text = stringResource(R.string.sign_up),
                                color = Color(0xFF6200EE),
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                    }

                    CustomizedButton(
                        text = R.string.login,
                        onclick = { viewModel.login(email, password) },
                        modifier2 = Modifier.padding(horizontal = 50.dp)
                    )
                }
            }
        }
    }
}



