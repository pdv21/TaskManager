package com.example.taskmanager.ui.screens
import androidx.compose.foundation.*
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.compose.ui.tooling.preview.*
import com.example.taskmanager.R
import com.example.taskmanager.buttonGradient
import com.example.taskmanager.buttonGradientEnd
import com.example.taskmanager.buttonGradientStart
import com.example.taskmanager.ui.component.CustomizedButton
import com.example.taskmanager.ui.component.RegisterTextField
import com.example.taskmanager.ui.component.TopBar

@Preview(showBackground = true)
@Composable
fun ForgotPassScreen(){
    var email by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .background(buttonGradientStart)
            ) {
                TopBar(
                    icon1 = R.drawable.short_left,
                    onClick1 = {},
                    onClick2 = {}
                )
            }
        }
    ) {innerpadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .background(Brush.verticalGradient(buttonGradient))
            ) {
                Column(
                    modifier = Modifier.padding(top = 50.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(R.drawable.mail),
                        contentDescription = null,
                        tint = buttonGradientEnd,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .padding(12.dp)
                    )

                    Text(
                        stringResource(R.string.reset_password),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(top = 20.dp)
                    )

                    Text(
                        stringResource(R.string.title_reset_pass),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Light,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .align(Alignment.Center),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.headline_reset_pass),
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 14.5.sp,
                        modifier = Modifier.padding(4.dp)
                    )

                    RegisterTextField(
                        R.string.enter_email,
                        R.string.email,
                        R.drawable.mail,
                        value = email,
                        onChange = { email = it },
                        modifier = Modifier.padding(vertical = 16.dp)
                    )

                    CustomizedButton(
                        text = R.string.send_otp,
                        onclick = {},
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 150.dp),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.support),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(0.dp)
                    )
                    Text(
                        text = stringResource(R.string.email_support),
                        color = buttonGradientStart,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(0.dp)
                    )
                }
            }
        }
    }
}