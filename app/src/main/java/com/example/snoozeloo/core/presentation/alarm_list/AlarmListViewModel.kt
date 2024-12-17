package com.example.snoozeloo.core.presentation.alarm_list

import androidx.lifecycle.ViewModel
import com.example.snoozeloo.core.data.AlarmRepository

class AlarmListViewModel(
    private val alarmRepository: AlarmRepository
) : ViewModel() {
    fun test(): String {
        return alarmRepository.getListOfAlarms().toString()
    }
}