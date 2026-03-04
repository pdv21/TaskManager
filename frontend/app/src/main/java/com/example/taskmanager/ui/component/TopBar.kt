package com.example.taskmanager.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*
import androidx.compose.ui.tooling.preview.*
import com.example.taskmanager.R

@Composable
fun TopBar(
    @DrawableRes icon1: Int,
    @DrawableRes icon2: Int,
    colorIcon1: Color = Color.Black,
    colorIcon2: Color = Color.Black,
    color: Color = Color.Transparent,
    onClick1: () -> Unit,
    onClick2: () -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(color)
            .statusBarsPadding()
            .padding(horizontal = 10.dp, vertical = 16.dp)
    ){
        IconButton(onClick = {onClick1()} ){
            Icon(
                painter = painterResource(icon1),
                contentDescription = null,
                tint = colorIcon1,
                modifier = Modifier.size(30.dp)
            )
        }

        Spacer(Modifier.weight(1f))

        IconButton(onClick = {onClick2()}) {
            Icon(
                painter = painterResource(icon2),
                contentDescription = null,
                tint = colorIcon2,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}