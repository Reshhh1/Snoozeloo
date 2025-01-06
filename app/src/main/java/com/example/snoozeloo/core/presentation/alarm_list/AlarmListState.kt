package com.example.snoozeloo.core.presentation.alarm_list

import com.example.snoozeloo.core.data.AlarmEntity

sealed class AlarmListState {
    data class Success(val alarms: List<AlarmEntity>) : AlarmListState()
}