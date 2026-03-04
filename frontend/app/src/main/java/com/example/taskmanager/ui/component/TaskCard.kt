package com.example.taskmanager.ui.component

import android.provider.CalendarContract.Colors
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.tooling.preview.*
import com.example.taskmanager.R
import com.example.taskmanager.buttonGradient
import com.example.taskmanager.data.TaskResponse

@Composable
fun TaskCard(
    task: TaskResponse
){
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(70.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ){
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .offset(12.dp)
                    .size(50.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Brush.linearGradient(buttonGradient)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.to_do_list_1__traced_),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(26.dp)
                )
            }

            Column(
                modifier = Modifier.offset(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    text = task.name,
                    style = MaterialTheme.typography.labelLarge,
                )
                Text(
                    text = stringResource(R.string.day_ago, task.start_date),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Light
                )
            }

            Spacer(Modifier.weight(1f))

            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = null,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

