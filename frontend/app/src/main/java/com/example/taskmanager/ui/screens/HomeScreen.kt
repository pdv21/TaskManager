package com.example.taskmanager.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.taskmanager.R
import com.example.taskmanager.ui.component.BottomBar
import com.example.taskmanager.ui.component.CardProject
import com.example.taskmanager.ui.component.TaskCard
import com.example.taskmanager.ui.component.TopBar
import com.example.taskmanager.model.TaskViewModel

enum class FilterType{
    ALL, PROGRESS, COMPLETED
}

@Composable
fun HomeScreen(
    name: String,
    navController: NavController,
    viewModel: TaskViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getTask()
    }
    val tasks by viewModel.tasks.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    var selectedFilter by remember { mutableStateOf(FilterType.ALL) }
    val filterTasks = when(selectedFilter){
        FilterType.ALL -> tasks
        FilterType.PROGRESS -> tasks.filter{it.status_name == "In Progress"}
        FilterType.COMPLETED -> tasks.filter{it.status_name == "DONE"}
    }

    Scaffold(
        topBar = {
            TopBar(
                icon1 = R.drawable.group,
                icon2 = R.drawable.account_circle_black_24dp_1,
                color = MaterialTheme.colorScheme.surfaceVariant,
                onClick1 = {

                },
                onClick2 = {

                }
            )
        },
        bottomBar = {
            BottomBar(
                navController = navController,
                homeIcon = R.drawable.home_outline,
                calendarIcon = R.drawable.calendar,
                notiIcon = R.drawable.notification_outline,
                searchIcon = R.drawable.frame_2605
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(R.string.hello, name),
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = stringResource(R.string.wish),
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light
                )

                Spacer(Modifier.height(20.dp))
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                FilterButton(
                    text = stringResource(R.string.my_Task),
                    isSelected = selectedFilter == FilterType.ALL,
                ) {
                    selectedFilter = FilterType.ALL
                }

                Spacer(Modifier.weight(1f))

                FilterButton(
                    text = stringResource(R.string.in_progress),
                    isSelected = selectedFilter == FilterType.PROGRESS
                ) {
                    selectedFilter = FilterType.PROGRESS
                }

                Spacer(Modifier.weight(1f))

                FilterButton(
                    text = stringResource(R.string.completed),
                    isSelected = selectedFilter == FilterType.COMPLETED
                ) {
                    selectedFilter = FilterType.COMPLETED
                }
            }

            LazyRow {
                items(filterTasks) { task ->
                    CardProject(task)
                }
            }

            Text(
                text = stringResource(R.string.progress),
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 12.dp)
            )

            when {
                isLoading -> {
                    Text("Loading...")
                }

                error != null -> {
                    Text("Error: $error")
                }

                else -> {
                    LazyColumn {
                        items(filterTasks) { task ->
                            TaskCard(task)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun FilterButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if(isSelected){
                MaterialTheme.colorScheme.onPrimary
            } else {
                Color.LightGray
            }
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 8.dp
        )
    ){
        Text(
            text,
            color = if(isSelected){
                MaterialTheme.colorScheme.primary
            } else {
                Color.White
            }
        )
    }
}
