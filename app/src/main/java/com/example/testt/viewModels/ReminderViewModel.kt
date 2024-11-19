package com.example.testt.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testt.domain.model.Reminder
import com.example.testt.domain.repositories.ReminderRepository
import com.example.testt.domain.repositories.WorkerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(
    private val repository: ReminderRepository,
    private val workRepository: WorkerRepository
) : ViewModel() {
    private val _remindersList = MutableStateFlow<List<Reminder>>(emptyList())
    val remindersList: StateFlow<List<Reminder>> = _remindersList

    private val _blurOn = MutableStateFlow<Boolean>(false)
    val blurOn = _blurOn

    private val _reminderByKey = MutableStateFlow<Reminder>(Reminder("", "", 0, 0, 0, 0, 0, ""))
    val reminderByKey = _reminderByKey


    init {
        getReminders()
    }

    private fun getReminders() {
        viewModelScope.launch {
            repository.getReminders().collect {
                _remindersList.value = it
            }
        }
    }

    fun upsertReminders(
        title: String,
        hour: Int,
        minute: Int,
        day: Int,
        month: Int,
        year: Int,
        repeat: String
    ) {
        viewModelScope.launch {
            val key = UUID.randomUUID().toString()
            repository.upsertReminder(Reminder(key, title, day, month, year, hour, minute, repeat))
            workRepository.addTaskToWorker(
                key,
                title,
                LocalDateTime.of(year, month, day, hour, minute),
                repeat
            )
        }
    }

    fun updateReminder(
        key: String,
        title: String,
        hour: Int,
        minute: Int,
        day: Int,
        month: Int,
        year: Int,
        repeat: String
    ) {
        viewModelScope.launch {
            repository.updateReminder(Reminder(key, title, day, month, year, hour, minute, repeat))
            workRepository.addTaskToWorker(
                key,
                title,
                LocalDateTime.of(year, month, day, hour, minute),
                repeat
            )
        }
    }

    fun removeReminder(key: String) {
        viewModelScope.launch {
            repository.removeReminder(key)
        }
    }

    fun setBlur() {
        _blurOn.value = !blurOn.value
    }

    fun getReminder(key: String) {
        viewModelScope.launch {
            repository.getReminderByKey(key).collect {
                _reminderByKey.value = it
            }

        }
    }

}