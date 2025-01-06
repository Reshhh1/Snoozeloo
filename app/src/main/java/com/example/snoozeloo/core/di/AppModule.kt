package com.example.snoozeloo.core.di

import com.example.snoozeloo.MainApplication
import com.example.snoozeloo.core.presentation.alarm_create.AlarmCreateViewModel
import com.example.snoozeloo.core.presentation.alarm_list.AlarmListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModelOf

import org.koin.dsl.module


val appModule = module {
    single {
        (androidApplication() as MainApplication).database.dao
    }
    viewModelOf(::AlarmCreateViewModel)
    viewModelOf(::AlarmListViewModel)

}