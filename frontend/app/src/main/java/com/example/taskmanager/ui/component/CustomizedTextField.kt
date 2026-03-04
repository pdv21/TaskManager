package com.example.taskmanager.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.*
import androidx.compose.ui.tooling.preview.*
import com.example.taskmanager.R
import kotlin.math.max

@Composable
fun CustomizedTextField(
    value: String,
    onChange: (String) -> Unit,
    @StringRes label: Int,
    singleLine: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    modifier: Modifier = Modifier
){
    TextField(
        value = value,
        onValueChange = onChange,
        label = {Text(stringResource(label))},
        singleLine = singleLine,
        maxLines = maxLines,
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun CustomizedTextFieldPreview(){
    CustomizedTextField(
        value = "",
        onChange = {},
        label = R.string.label_name
    )
}