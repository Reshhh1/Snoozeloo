package com.example.snoozeloo.core.presentation.alarm_create


data class AlarmCreateState(
    val name: String = "",
    val isTypingName: Boolean = false,
    val time: AlarmTime = AlarmTime(
        initialHour = "",
        initialMinute = ""
    )
)
