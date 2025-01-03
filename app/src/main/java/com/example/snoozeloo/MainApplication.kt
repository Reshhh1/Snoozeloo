package com.example.snoozeloo

import android.app.Application
import com.example.snoozeloo.core.data.AlarmDatabase
import com.example.snoozeloo.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication: Application() {

    lateinit var database: AlarmDatabase

    override fun onCreate() {
        super.onCreate()
        database = AlarmDatabase.createDatabase(this)
        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(appModule)
        }
    }
}