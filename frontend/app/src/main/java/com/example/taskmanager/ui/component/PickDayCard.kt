package com.example.taskmanager.ui.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.tooling.preview.*
import androidx.navigation.NavController
import com.example.taskmanager.R
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun PickDayCard(
    navController: NavController,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
){
    val currentMonth = YearMonth.from(selectedDate)
    val daysInMonth = remember(currentMonth){
        (1..currentMonth.lengthOfMonth()).map{
            currentMonth.atDay(it)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.15f),
                        Color.Transparent
                    )
                )
            )
    ){
        Column(
            modifier = Modifier.padding(bottom = 4.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(
                    topEnd = 0.dp,
                    topStart = 0.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                ),
                elevation = CardDefaults.cardElevation(

                )
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    HeaderRow(
                        navController = navController,
                        month = currentMonth
                    )
                    PickerDay(
                        days = daysInMonth,
                        selectedDate = selectedDate,
                        onDateSelected = onDateSelected
                    )
                }
            }
        }
    }
}

@Composable
fun HeaderRow(
    navController: NavController,
    month: YearMonth
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
    ){
        Text(
            text = month.month.getDisplayName(
                TextStyle.SHORT,
                Locale.ENGLISH
            )+", ${month.year}",
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(Modifier.weight(1f))
        CustomizedButton(
            text = R.string.add_task,
            onclick = {navController.navigate("createTask")},
            modifier2 = Modifier.padding(horizontal = 20.dp)
        )
    }
}

@Composable
fun PickerDay(
    days: List<LocalDate>,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
){
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp)
    ){
        items(days){date ->
            DayItem(
                date = date,
                isSelected = date == selectedDate,
                onClick = {onDateSelected(date)}
            )
        }
    }
}

@Composable
fun DayItem(
    date: LocalDate,
    isSelected: Boolean,
    onClick: () -> Unit
){
    val background = if(isSelected) Color(0xFFEDE7FF) else Color.Transparent
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(background)
            .clickable{onClick}
            .padding(vertical = 12.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = date.dayOfWeek.getDisplayName(
                TextStyle.SHORT,
                Locale.ENGLISH
            ),
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = date.dayOfMonth.toString(),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
    }
}
