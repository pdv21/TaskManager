package com.example.taskmanager.ui.screens

import androidx.compose.foundation.*
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
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.taskmanager.buttonGradient
import com.example.taskmanager.R
import com.example.taskmanager.buttonGradientEnd
import com.example.taskmanager.ui.component.CustomizedButton
import com.example.taskmanager.ui.component.PasswordTextField
import com.example.taskmanager.ui.component.RegisterTextField

@Composable
fun RegisterScreen(
    navController: NavController
){
    var scrollState = rememberScrollState()

    var full_name by remember{ mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var position by remember { mutableStateOf("") }
    var department by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }

    var checkState by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
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

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 8.dp)
                    ){
                        Checkbox(
                            checked = checkState,
                            onCheckedChange = {checkState = it},
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(0xFF6200EE), // Màu khi tích vào
                                uncheckedColor = Color.LightGray  // Màu viền khi chưa tích
                            )
                        )
                        Text(
                            "I agree to the Terms & Conditions and Privacy Policy",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    CustomizedButton(
                        R.string.sign_up,
                        onclick = {
                            if(checkState){}
                        },
                        modifier = Modifier
                            .width(220.dp) ,
                        shape = RoundedCornerShape(16.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ){
            Text(stringResource(R.string.already_account))
            TextButton(onClick = {navController.navigate("login")}) {
                Text(
                    text = stringResource(R.string.sign_in),
                    color = Color(0xFF6200EE), // Màu tím nổi bật
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}