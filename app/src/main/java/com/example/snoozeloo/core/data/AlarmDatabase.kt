package com.example.snoozeloo.core.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

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