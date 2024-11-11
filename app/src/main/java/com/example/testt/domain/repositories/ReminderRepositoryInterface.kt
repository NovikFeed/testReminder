package com.example.testt.domain.repositories

import com.example.testt.data.model.ReminderDBO
import com.example.testt.domain.model.Reminder
import kotlinx.coroutines.flow.Flow

interface ReminderRepositoryInterface {
    suspend fun getReminders() : Flow<List<Reminder>>
    suspend fun getReminderByKey(key : String) : Flow<Reminder>
    suspend fun upsertReminder(reminder : Reminder)
    suspend fun removeReminder(key: String)
}