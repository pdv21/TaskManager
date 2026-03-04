package com.example.taskmanager.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanager.R
import com.example.taskmanager.buttonGradient

@Composable
fun CustomizedButton(
    @StringRes text: Int,
    onclick: () -> Unit,
    modifier: Modifier = Modifier,
    modifier2: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(56.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = buttonGradient
                ),
                shape = RoundedCornerShape(28.dp)
            )
            .clickable { onclick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(text),
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier2
        )
    }

}

@Preview
@Composable
fun ButtonPreview(){
    CustomizedButton(R.string.app_name, {})
}