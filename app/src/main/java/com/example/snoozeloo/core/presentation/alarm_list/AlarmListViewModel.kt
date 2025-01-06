package com.example.snoozeloo.core.presentation.alarm_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snoozeloo.core.data.AlarmDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlarmListViewModel(
    private val dao: AlarmDao
) : ViewModel() {
    private val _state = MutableStateFlow(AlarmListState.Success(emptyList()))
    val state: StateFlow<AlarmListState> = _state

    init {
        viewModelScope.launch {
            dao.getAlarmsOrderedByDate().collect {
                _state.value = AlarmListState.Success(it)
            }
        }
    }
}