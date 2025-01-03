package com.example.snoozeloo.core.presentation.alarm_create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snoozeloo.core.data.AlarmDao
import com.example.snoozeloo.core.data.AlarmEntity
import com.example.snoozeloo.core.data.AlarmModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AlarmCreateViewModel(
    private val dao: AlarmDao
): ViewModel() {

    private val _state = MutableStateFlow(AlarmCreateState())
    val state = _state.asStateFlow()

    fun onEvent(event: AlarmEvent) {
        when(event) {
            is AlarmEvent.SetAlarmName -> {
                _state.update { it.copy(name = event.name) }
            }
            is AlarmEvent.SetAlarmHours -> setAlarmHours(event.hours)
            is AlarmEvent.SetAlarmMinutes -> setAlarmMinutes(event.minutes)
            is AlarmEvent.IsTypingName -> {
                _state.update { it.copy(isTypingName = event.isTyping) }
            }
            is AlarmEvent.SaveAlarm -> createAlarm()
        }
    }

    private fun setAlarmHours(hours: String) {
        val alarmModel = AlarmModel(
            name = state.value.name,
            time = AlarmTime(
                initialHour = hours,
                initialMinute = state.value.time.initialMinute
            )
        )
        if (!alarmModel.time.validateTimeHours()) return
        _state.update {
            it.copy(time = it.time.copy(initialHour = alarmModel.getValidTimeHours()))
        }
    }

    private fun setAlarmMinutes(minutes: String) {
        val alarmModel = AlarmModel(
            name = state.value.name,
            time = AlarmTime(
                initialHour = state.value.time.initialHour,
                initialMinute = minutes
            )
        )
        if (!alarmModel.time.validateTimeMinutes()) return
        _state.update { it.copy(time = it.time.copy(initialMinute = alarmModel.getValidTimeMinutes())) }
    }

    private fun createAlarm() {
        val alarmModel = AlarmModel(
            name = state.value.name,
            time = state.value.time
        ).getAlarmModelOrDefault()

        val alarmEntity = AlarmEntity(
            title = alarmModel.name,
            initialHour = alarmModel.time.initialHour,
            initialMinute = alarmModel.time.initialMinute,
            createdAt = System.currentTimeMillis()
        )
        viewModelScope.launch {
            dao.upsertAlarm(alarmEntity)
        }
        _state.update {
            it.copy(
                name = "",
                isTypingName = false,
                time = AlarmTime(
                    initialHour = "",
                    initialMinute = ""
                )
            )
        }
    }
}