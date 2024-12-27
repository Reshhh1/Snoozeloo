package com.example.snoozeloo.core.presentation.alarm_create

sealed class AlarmEvent {
    data object SaveAlarm: AlarmEvent()
    data class SetAlarmName(val name: String): AlarmEvent()
    data class IsTypingName(val isTyping: Boolean): AlarmEvent()
    data class SetAlarmHours(val hours: String): AlarmEvent()
    data class SetAlarmMinutes(val minutes: String): AlarmEvent()
}