package com.example.testt.domain.repositories

import com.example.testt.data.ReminderDatabase
import com.example.testt.data.utils.toReminder
import com.example.testt.data.utils.toReminderDBO
import com.example.testt.domain.model.Reminder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReminderRepository @Inject constructor(
    private val reminderDatabase: ReminderDatabase
) : ReminderRepositoryInterface{
    override suspend fun getReminders(): Flow<List<Reminder>> {
        return reminderDatabase.reminderDAO.getReminders().map { it.map { it.toReminder() } }
    }

    override suspend fun getReminderByKey(key: String): Flow<Reminder> {
        return flow {
            val data = reminderDatabase.reminderDAO.getReminderByKey(key)
            emit(data.toReminder())
        }
    }

    override suspend fun upsertReminder(reminder: Reminder) {
        reminderDatabase.reminderDAO.upsertReminder(reminder.toReminderDBO())
    }

    override suspend fun removeReminder(key: String) {
        reminderDatabase.reminderDAO.removeReminder(key)
    }

    override suspend fun updateReminder(reminder: Reminder) {
        reminderDatabase.reminderDAO.updateReminder(reminder.toReminderDBO())
    }

}