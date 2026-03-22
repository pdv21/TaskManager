package com.example.taskmanager.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.taskmanager.R
import com.example.taskmanager.buttonGradient
import com.example.taskmanager.data.CreateTaskRequest
import com.example.taskmanager.ui.component.CategoryButton
import com.example.taskmanager.ui.component.CustomizedButton
import com.example.taskmanager.ui.component.CustomizedTextField
import com.example.taskmanager.ui.component.TopBar
import com.example.taskmanager.model.CategoryViewModel
import com.example.taskmanager.model.TaskViewModel
import com.example.taskmanager.remote.TokenManager

@Composable
fun CreateTaskScreen(
    navController: NavController,
    viewModel: TaskViewModel = viewModel(),
    categoryModel: CategoryViewModel = viewModel()
) {
    var selectedIndex by remember { mutableStateOf(0) }
    var name by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var start_date by remember { mutableStateOf("") }
    var end_date by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val createSuccess by viewModel.createSuccess.collectAsState()
    val categories by categoryModel.category.collectAsState()

    LaunchedEffect(createSuccess) {
        if (createSuccess) {
            navController.popBackStack()
        }
    }
    LaunchedEffect(Unit) {
        categoryModel.getCategory()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(buttonGradient))
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopBar(
                    icon1 = R.drawable.short_left,
                    icon2 = R.drawable.frame_2605,
                    colorIcon1 = Color.White,
                    colorIcon2 = Color.White,
                    onClick1 = {
                        navController.popBackStack()
                    },
                    onClick2 = {

                    }
                )
            }
        ) { innerPadding ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(innerPadding),
            ) {
                Box(
                    modifier = Modifier
                        .background(Brush.verticalGradient(buttonGradient))
                        .padding(top = 10.dp, bottom = 80.dp),
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        TextField(
                            value = name,
                            onValueChange = { name = it },
                            label = {
                                Text(
                                    stringResource(R.string.label_name),
                                    color = Color.White
                                )},
                            textStyle = TextStyle(color = Color.White),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent,
                                errorContainerColor = Color.Transparent
                            )
                        )

                        Spacer(Modifier.height(20.dp))

                        TextField(
                            value = date,
                            onValueChange = { date = it },
                            label = {
                                Text(
                                    stringResource(R.string.label_date),
                                    color = Color.White
                                )},
                            textStyle = TextStyle(color = Color.White),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent,
                                errorContainerColor = Color.Transparent
                            )
                        )
                    }
                }

                Card(
                    colors = CardDefaults.cardColors(Color.White),
                    modifier = Modifier
                        .fillMaxSize(),
                    shape = RoundedCornerShape(
                        topEnd = 16.dp,
                        topStart = 16.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    ),
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.padding(vertical = 10.dp)
                        ) {
                            CustomizedTextField(
                                value = start_date,
                                onChange = {start_date = it},
                                label = R.string.start_time,
                                modifier = Modifier.weight(1f)
                            )

                            CustomizedTextField(
                                value = end_date,
                                onChange = {end_date = it},
                                label = R.string.end_time,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Spacer(Modifier.height(50.dp))

                        CustomizedTextField(
                            value = description,
                            onChange = {description = it},
                            singleLine = false,
                            label = R.string.description,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(Modifier.weight(1f))

                        Text(
                            text = stringResource(R.string.category),
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.offset(x = -150.dp)
                        )

                        LazyHorizontalGrid(
                            rows = GridCells.Fixed(2),
                            modifier = Modifier.height(120.dp),
                            userScrollEnabled = false,
                        ) {
                            items(categories) { item ->
                                CategoryButton(
                                    isShow = selectedIndex == item.id,
                                    text = item.name,
                                    onClick = {
                                        selectedIndex = item.id
                                    },
                                    modifier = Modifier.padding(10.dp)
                                )
                            }
                        }

                        CustomizedButton(
                            text = R.string.create_Task,
                            onclick = {
                                val request = CreateTaskRequest(
                                    title = name,
                                    description = description,
                                    start_date = start_date,
                                    end_date = end_date,
                                    status_id = 1,
                                    category_id = selectedIndex
                                )
                                viewModel.createTask(
                                    token = TokenManager.getToken() ?: "",
                                    request = request
                                )
                            },
                            modifier = Modifier.padding(20.dp),
                            modifier2 = Modifier.padding(horizontal = 50.dp)
                        )
                    }
                }
            }
        }
    }
}
