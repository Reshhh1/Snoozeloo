package com.example.snoozeloo.core.presentation.alarm_create

import androidx.lifecycle.ViewModel
import com.example.snoozeloo.core.data.AlarmRepository

class AlarmCreateViewModel(
    private val alarmRepository: AlarmRepository
): ViewModel() {

    fun test(): String {
        return "e"
    }
}