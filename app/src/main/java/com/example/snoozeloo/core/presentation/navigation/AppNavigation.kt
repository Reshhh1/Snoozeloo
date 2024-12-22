package com.example.snoozeloo.core.presentation.navigation

import AlarmListScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.snoozeloo.core.presentation.alarm_create.AlarmCreateScreen
import com.example.snoozeloo.core.presentation.alarm_create.AlarmCreateViewModel
import com.example.snoozeloo.core.presentation.navigation.destination.AlarmCreate
import com.example.snoozeloo.core.presentation.navigation.destination.AlarmList
import org.koin.androidx.compose.koinViewModel

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
            val viewModel = koinViewModel<AlarmCreateViewModel>()
            val state by viewModel.state.collectAsState()
            AlarmCreateScreen(
                navigateToList = { navController.navigate(AlarmList) },
                onEvent = viewModel::onEvent,
                state = state
            )
        }
    }
}