package com.example.snoozeloo.core.di

import com.example.snoozeloo.MainApplication
import com.example.snoozeloo.core.data.AlarmRepository
import com.example.snoozeloo.core.data.AlarmRepositoryImpl
import com.example.snoozeloo.core.presentation.alarm_list.AlarmListViewModel
import com.example.snoozeloo.core.presentation.alarm_create.AlarmCreateViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {
    single {
        (androidApplication() as MainApplication).database.dao
    }

    singleOf(::AlarmRepositoryImpl) { bind<AlarmRepository>() }

    viewModelOf(::AlarmListViewModel)
    viewModelOf(::AlarmCreateViewModel)
}