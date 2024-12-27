package com.example.snoozeloo.core.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "alarm"
)
data class AlarmEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val initialHour: String = "00",
    val initialMinute: String = "00",
    val createdAt: Long
)
