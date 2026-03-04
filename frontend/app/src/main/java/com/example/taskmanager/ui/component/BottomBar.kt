package com.example.taskmanager.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*
import androidx.compose.ui.tooling.preview.*
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.taskmanager.R

@Composable
fun BottomBar(
    navController: NavController,
    @DrawableRes homeIcon: Int,
    @DrawableRes calendarIcon: Int,
    @DrawableRes notiIcon: Int,
    @DrawableRes searchIcon: Int
){
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ){
        Spacer(Modifier.weight(1f))

        IconButton(
            onClick = {
                navController.navigate("home") {
                    launchSingleTop = true
                }
            }
        ) {
            Icon(
                painter = painterResource(homeIcon),
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = if (currentRoute == "home")
                    MaterialTheme.colorScheme.primary
                else
                    Color.Gray.copy(alpha = 0.5f)
            )
        }

        Spacer(Modifier.weight(1f))

        IconButton(
            onClick = {
                navController.navigate("calendar"){
                    launchSingleTop = true
            }
            }
        ) {
            Icon(
                painter = painterResource(calendarIcon),
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = if (currentRoute == "calendar")
                    MaterialTheme.colorScheme.primary
                else
                    Color.Gray.copy(alpha = 0.5f)
            )
        }

        Spacer(Modifier.weight(1f))

        IconButton(
            onClick = {
                navController.navigate("notify"){
                    launchSingleTop = true
                }
            }
        ) {
            Icon(
                painter = painterResource(notiIcon),
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = if (currentRoute == "notify")
                    MaterialTheme.colorScheme.primary
                else
                    Color.Gray.copy(alpha = 0.5f)
            )
        }

        Spacer(Modifier.weight(1f))

        IconButton(
            onClick = {
                navController.navigate("search") {
                    launchSingleTop = true
                }
            }
        ) {
            Icon(
                painter = painterResource(searchIcon),
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = if (currentRoute == "search")
                    MaterialTheme.colorScheme.primary
                else
                    Color.Gray.copy(alpha = 0.5f)
            )
        }

        Spacer(Modifier.weight(1f))
    }
}