package com.example.taskmanager.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.tooling.preview.*
import com.example.taskmanager.buttonGradient
import com.example.taskmanager.R
import com.example.taskmanager.buttonGradientEnd
import com.example.taskmanager.ui.component.CustomizedButton
import com.example.taskmanager.ui.component.PasswordTextField
import com.example.taskmanager.ui.component.RegisterTextField

@Preview(showBackground = true)
@Composable
fun RegisterPage(){
    var scrollState = rememberScrollState()

    var full_name by remember{ mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var position by remember { mutableStateOf("") }
    var department by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ){
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .background(brush = Brush.verticalGradient(buttonGradient)),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(R.drawable.user_02),
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
                        stringResource(R.string.create_account),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(top = 20.dp)
                    )

                    Text(
                        stringResource(R.string.sign_up_to),
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
                    .padding(top = 250.dp),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    RegisterTextField(
                        R.string.enter_name,
                        R.string.full_name,
                        R.drawable.user_02,
                        value = full_name,
                        onChange = {
                            full_name = it
                        }
                    )

                    RegisterTextField(
                        R.string.enter_email,
                        R.string.email,
                        R.drawable.mail,
                        value = email,
                        onChange = {email = it}
                    )

                    RegisterTextField(
                        R.string.enter_phone,
                        R.string.phone,
                        R.drawable.phone,
                        value = phone,
                        onChange = {phone = it}
                    )

                    RegisterTextField(
                        R.string.enter_position,
                        R.string.position,
                        R.drawable.bag,
                        value = position,
                        onChange = {position = it}
                    )

                    RegisterTextField(
                        R.string.enter_department,
                        R.string.department,
                        R.drawable.bag,
                        value = department,
                        onChange = {department = it}
                    )

                    PasswordTextField(
                        R.string.create_pass,
                        R.string.password,
                        R.drawable.lock,
                        value = pass,
                        onChange = {pass = it}
                    )

                    PasswordTextField(
                        R.string.confirm_pass,
                        R.string.confirm,
                        R.drawable.lock,
                        value = confirmPass,
                        onChange = {confirmPass = it}
                    )

                    CustomizedButton(
                        R.string.sign_up,
                        onclick = {},
                        modifier = Modifier
                            .width(220.dp) ,
                        shape = RoundedCornerShape(16.dp)
                    )
                }
            }
        }
    }
}