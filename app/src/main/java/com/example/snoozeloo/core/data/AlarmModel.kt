package com.example.snoozeloo.core.data

import com.example.snoozeloo.core.presentation.alarm_create.AlarmTime

data class AlarmModel(
    val name: String,
    val time: AlarmTime
) {

    fun getValidTimeMinutes(): String {
        return if (time.validateTimeHours()) {
            time.timeNormalizer(time.initialMinute, 59)
        } else time.initialHour
    }

    fun getValidTimeHours(): String {
        return if (time.validateTimeHours()) {
            time.timeNormalizer(time.initialHour, 23)
        } else time.initialHour
    }
}