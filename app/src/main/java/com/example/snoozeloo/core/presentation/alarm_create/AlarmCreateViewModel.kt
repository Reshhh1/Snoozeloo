package com.example.snoozeloo.core.presentation.alarm_create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snoozeloo.core.data.AlarmDao
import com.example.snoozeloo.core.data.AlarmEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

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
            is AlarmEvent.SetAlarmHours -> {
                _state.update { it.copy(time = it.time.copy(initialHour = event.hours)) }
            }
            is AlarmEvent.SetAlarmMinutes -> {
                _state.update { it.copy(time = it.time.copy(initialMinute = event.minutes)) }
            }
            is AlarmEvent.IsTypingName -> {
                _state.update { it.copy(isTypingName = event.isTyping) }
            }
            is AlarmEvent.SaveAlarm -> createAlarm()
        }
    }

    private fun createAlarm() {
        val name = state.value.name
        val time = state.value.time

        /**
         * TODO: Add validation / data mapper
         * Validation:
         *  - Name can be empty
         *  - Time can be empty (because it will be set to the default time: 00:00)
         *  - Time hours / minutes have a max of 2 digits
         *  - Time hours value goes from the number 0-24, values that arent the number 1 or 2
         *  automatically get a 0 in front of it so: 8 -> 08, 9 -> 09 ON INPUT CHANGE.
         *  This isn't done for 1 and 2 because the user might still enter a 2 digit value.
         *  But if upon confirmation the value is still 1 it will get saved as 01
         *  - Time minutes value goes from the number 0-59 with the same rules as hours
         */
        val alarmEntity = AlarmEntity(
            title = name,
            initialHour = time.initialHour,
            initialMinute = time.initialMinute,
            createdAt = Date.from(Instant.now()).time
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