package com.example.taskmanager.ui.component
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.tooling.preview.*
import java.time.LocalDate
import com.example.taskmanager.R
import com.example.taskmanager.buttonGradient
import com.example.taskmanager.data.TaskResponse
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun CardProject(
    project: TaskResponse
){
    Card(
        modifier = Modifier
            .size(300.dp)
            .padding(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.linearGradient(buttonGradient))
        ) {
            Column(
                modifier = Modifier.padding(start = 30.dp, bottom = 30.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                Color.White.copy(alpha = 0.2f)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.project_management),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .size(32.dp)
                        )
                    }

                    Text(
                        text = stringResource(R.string.project, project.id),
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }

                Spacer(Modifier.weight(1f))

                Text(
                    text = project.name,
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(Modifier.weight(1f))

            }
        }
    }
}

fun String.toFormattedDate(): String {
    val inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val outputFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy", Locale.ENGLISH)

    val parsedDate = LocalDate.parse(this, inputFormatter)
    return parsedDate.format(outputFormatter)
}
