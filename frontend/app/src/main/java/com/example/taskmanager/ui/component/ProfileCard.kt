package com.example.taskmanager.ui.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.*
import androidx.compose.ui.tooling.preview.*
import com.example.taskmanager.TaskManagerTheme
import com.example.taskmanager.buttonGradient

@Composable
fun ProfileCard(
    title: String,
    count: Int
){
    Card(
        modifier = Modifier.padding(8.dp)
    ){
        Column(
            modifier = Modifier
                .width(110.dp)
                .background(Color.White)
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
            Text(
                text = "$count",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black
            )
        }
    }
}