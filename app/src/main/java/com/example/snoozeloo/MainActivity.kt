package com.example.snoozeloo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.snoozeloo.core.presentation.navigation.AppNavigation
import com.example.snoozeloo.ui.theme.SnoozelooTheme
import org.koin.androidx.compose.KoinAndroidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SnoozelooTheme {
                KoinAndroidContext {
                    Scaffold(
                        modifier = Modifier.fillMaxSize()
                            .background(Color(0xFFF6F6F6)),
                    ) { innerPadding ->
                        Column(
                            Modifier.padding(innerPadding)
                        ) {
                            AppNavigation()
                        }
                    }
                }
            }
        }
    }
}