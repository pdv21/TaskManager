package com.example.taskmanager.ui.component

import android.R
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.*
import androidx.compose.ui.tooling.preview.*
import com.example.taskmanager.buttonGradient

@Composable
fun CategoryButton(
    isShow: Boolean,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .height(30.dp)
            .width(100.dp)
            .background(
                if (isShow)
                    Brush.verticalGradient(buttonGradient)
                else
                    Brush.verticalGradient(
                        listOf(MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.background)
                    ),
                shape = RoundedCornerShape(28.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isShow) Color.White else Color.Black
        )
    }
}