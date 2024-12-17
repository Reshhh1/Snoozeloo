package com.example.snoozeloo.core.di

import com.example.snoozeloo.core.data.AlarmRepository
import com.example.snoozeloo.core.data.AlarmRepositoryImpl
import com.example.snoozeloo.core.presentation.alarm_list.AlarmListViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {
    singleOf(::AlarmRepositoryImpl) { bind<AlarmRepository>() }
    viewModelOf(::AlarmListViewModel)
}