package com.example.taskmanager.ui.screens

import android.R.style
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.taskmanager.R
import com.example.taskmanager.data.TaskResponse
import com.example.taskmanager.ui.component.BottomBar
import com.example.taskmanager.ui.component.PickDayCard
import com.example.taskmanager.ui.component.TaskCard
import com.example.taskmanager.ui.component.TopBar
import com.example.taskmanager.ui.data.model.TaskViewModel
import java.time.LocalDate
import androidx.compose.runtime.getValue

@Composable
fun TaskScreen(
    navController: NavController,
    viewModel: TaskViewModel = viewModel()
){
    val tasks by viewModel.tasks.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getTask()
    }

    val date: LocalDate = LocalDate.now()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            TopBar(
                icon1 = R.drawable.short_left_1,
                icon2 = R.drawable.frame_2605,
                color = Color.White,
                onClick1 = {
                    navController.popBackStack()
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
    ) {innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
        ) {
            PickDayCard(
                navController = navController,
                selectedDate = date,
                onDateSelected = {}
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight()
                )
                {
                    Text(
                        text = stringResource(R.string.tasks),
                        style = MaterialTheme.typography.headlineSmall,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
                    )
                    when {
                        isLoading -> {
                            Text("Loading...")
                        }

                        error != null -> {
                            Text("Error: $error")
                        }

                        else -> {
                            LazyColumn(
                                modifier = Modifier.padding(horizontal = 16.dp)
                            ) {
                                items(tasks) { task ->
                                    TaskCard(task)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
