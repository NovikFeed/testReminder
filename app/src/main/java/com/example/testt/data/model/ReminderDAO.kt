package com.example.testt.data.model

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ReminderDAO {
    @Upsert
    suspend fun upsertReminder(reminder: ReminderDBO)

    @Query("SELECT * FROM ReminderDBO")
    suspend fun getReminders(): List<ReminderDBO>

    @Query("SELECT * FROM ReminderDBO WHERE uniqueKey = :key")
    suspend fun getReminderByKey(key: String): ReminderDBO

    @Query("DELETE FROM ReminderDBO WHERE uniqueKey = :key ")
    suspend fun removeReminder(key: String)
}