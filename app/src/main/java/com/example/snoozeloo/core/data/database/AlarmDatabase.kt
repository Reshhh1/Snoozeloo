package com.example.snoozeloo.core.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.snoozeloo.core.data.dao.AlarmDao
import com.example.snoozeloo.core.data.entity.AlarmEntity

@Database(
    entities = [AlarmEntity::class],
    version = 1, //For data migration
    exportSchema = false
)
abstract class AlarmDatabase: RoomDatabase() {

    abstract val dao: AlarmDao

    companion object {
        @Volatile
        private var Instance: AlarmDatabase? = null

        fun createDatabase(context: Context): AlarmDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AlarmDatabase::class.java,
                    "alarm_database"
                ).build().also { Instance = it }
            }
        }
    }
}