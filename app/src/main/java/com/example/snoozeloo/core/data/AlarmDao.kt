package com.example.snoozeloo.core.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao {

    //Upsert is a combination of Insert and Update
    @Upsert
    suspend fun upsertAlarm(alarmEntity: AlarmEntity)

    /**
     * Flow is a stream of data that can be collected over time. Which means
     * that when the data changes, the UI will be updated automatically.
     */
    @Query("SELECT * FROM alarm ORDER BY initialHour ASC, initialMinute ASC")
    fun getAlarmsOrderedByDate(): Flow<List<AlarmEntity>>
}