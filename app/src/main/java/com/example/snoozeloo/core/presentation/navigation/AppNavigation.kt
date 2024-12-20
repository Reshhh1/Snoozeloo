package com.example.snoozeloo.core.presentation.navigation

import AlarmListScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.snoozeloo.core.presentation.alarm_create.AlarmCreateScreen
import kotlinx.serialization.Serializable

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AlarmList
    ) {
        composable<AlarmList> {
            AlarmListScreen(
                navigateToAlarmCreate = { navController.navigate(AlarmCreate) }
            )
        }
        composable<AlarmCreate> {
            AlarmCreateScreen(
                navigateToList = { navController.navigate(AlarmList) }
            )
        }
    }
}

@Serializable
object AlarmList

@Serializable
object AlarmCreate