package com.example.snoozeloo.core.data

data class Alarm(
    val id: Int,
    val title: String,
    val time: String
)

interface AlarmRepository {
    fun addAlarm(alarm: Alarm)
    fun getListOfAlarms(): List<Alarm>
}

class AlarmRepositoryImpl : AlarmRepository {
    private val _alarms = listOf<Alarm>(
        Alarm(1, "Test-Alarm", "11-11-2024")
    )

    override fun addAlarm(alarm: Alarm) {
        _alarms.plus(alarm)
    }

    override fun getListOfAlarms(): List<Alarm> {
        return _alarms
    }
}