package com.example.taskmanager.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.*
import androidx.compose.ui.tooling.preview.*
import com.example.taskmanager.R

@Composable
fun InfoCard(
    @DrawableRes icon: Int,
    @StringRes title: Int,
    content: String,
    line: Boolean = true
){
    Column(
        modifier = Modifier.padding(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painterResource(icon),
                contentDescription = null,
                tint = Color.Blue,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFEBECFE))
                    .padding(8.dp)
            )
            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(
                    stringResource(title),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
                Text(
                    content,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        if(line) {
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp)
            )
        }
    }
}