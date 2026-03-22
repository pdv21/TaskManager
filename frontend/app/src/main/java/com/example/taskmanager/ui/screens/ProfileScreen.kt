package com.example.taskmanager.ui.screens

import android.content.Context
import android.graphics.drawable.Icon
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.compose.ui.tooling.preview.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.taskmanager.ui.component.ProfileCard
import com.example.taskmanager.R
import com.example.taskmanager.buttonGradient
import com.example.taskmanager.buttonGradientEnd
import com.example.taskmanager.buttonGradientStart
import com.example.taskmanager.model.UserViewModel
import com.example.taskmanager.ui.component.CustomizedButton
import com.example.taskmanager.ui.component.InfoCard
import com.example.taskmanager.ui.component.TopBar
import kotlin.contracts.contract

@Composable
fun ProfileScreen(
    navController: NavController,
    userViewModel: UserViewModel = viewModel()
){
    LaunchedEffect(Unit) {
        userViewModel.getUser()
    }
    val user by userViewModel.user.collectAsState()

    var scrollState = rememberScrollState()
    val quotes = listOf(
        R.string.quote_1,
        R.string.quote_2,
        R.string.quote_3,
        R.string.quote_4
    )
    val randomQuote = quotes.random()

    fun formatDate(date: String?): String {
        if (date.isNullOrEmpty()) return ""

        return try {
            val instant = java.time.Instant.parse(date)
            val formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")
            instant
                .atZone(java.time.ZoneId.systemDefault())
                .format(formatter)
        } catch (e: Exception) {
            ""
        }
    }

    fun getAddress(context: Context, lat: Double?, lng: Double?): String {
        if (lat == null || lng == null) return ""

        return try {
            val geocoder = android.location.Geocoder(context)
            val addresses = geocoder.getFromLocation(lat, lng, 1)

            if (!addresses.isNullOrEmpty()) {
                val address = addresses[0]
                "${address.adminArea}, ${address.countryName}"
            } else ""
        } catch (e: Exception) {
            ""
        }
    }
    val context = LocalContext.current
    val locationText = remember(user?.latitude, user?.longitude?:"") {
        getAddress(context, user?.latitude, user?.longitude)
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
                    icon2 = R.drawable.group,
                    colorIcon1 = Color.White,
                    colorIcon2 = Color.White,
                    onClick1 = {
                        navController.popBackStack()
                    },
                    onClick2 = {},
                )
            }
        ) { innerpadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(innerpadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = user?.full_name?:"",
                    color = Color.White,
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = user?.title?:"",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Light
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(16.dp)
                ) {
                    ProfileCard(stringResource(R.string.total), 29)
                    ProfileCard(stringResource(R.string.completed), 29)
                    ProfileCard(stringResource(R.string.overdue), 29)
                }
                Spacer(Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(){
                        OutlinedCard(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                stringResource(randomQuote),
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.padding(8.dp),
                                textAlign = TextAlign.Justify
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CustomizedButton(
                                text = R.string.edit_profile,
                                onclick = {},
                                modifier = Modifier
                                    .width(150.dp),
                                height = 35,
                                shape = RoundedCornerShape(
                                    topEnd = 8.dp,
                                    topStart = 8.dp,
                                    bottomStart = 8.dp,
                                    bottomEnd = 8.dp
                                )
                            )

                            Spacer(Modifier.width(24.dp))

                            CustomizedButton(
                                text = R.string.share,
                                onclick = {},
                                modifier = Modifier
                                    .width(150.dp),
                                height = 35,
                                shape = RoundedCornerShape(
                                    topEnd = 8.dp,
                                    topStart = 8.dp,
                                    bottomStart = 8.dp,
                                    bottomEnd = 8.dp
                                )
                            )
                        }
                        OutlinedCard(
                            modifier = Modifier
                                .padding(vertical = 10.dp, horizontal = 20.dp)
                                .fillMaxSize()
                        ) {
                            Text(
                                stringResource(R.string.contact),
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(8.dp)
                            )

                            InfoCard(
                                icon = R.drawable.mail,
                                title = R.string.email,
                                content = user?.email?:""
                            )
                            InfoCard(
                                icon = R.drawable.phone,
                                title = R.string.phone,
                                content = user?.email?:""
                            )
                            InfoCard(
                                icon = R.drawable.location,
                                title = R.string.location,
                                content = locationText,
                                line = false
                            )
                        }
                        OutlinedCard(
                            modifier = Modifier
                                .padding(vertical = 10.dp, horizontal = 20.dp)
                                .fillMaxSize()
                        ) {
                            Text(
                                stringResource(R.string.professional_detail),
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(8.dp)
                            )

                            InfoCard(
                                icon = R.drawable.bag,
                                title = R.string.department,
                                content = user?.department?:""
                            )
                            InfoCard(
                                icon = R.drawable.calendar_profile,
                                title = R.string.join_date,
                                content = formatDate(user?.create_at),
                                line = false
                            )
                        }
                    }
                }
            }
        }
    }
}