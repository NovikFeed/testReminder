package com.example.testt.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.testt.data.model.ReminderDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDAO {
    @Upsert
    suspend fun upsertReminder(reminder: ReminderDBO)

    @Query("SELECT * FROM ReminderDBO")
    fun getReminders(): Flow<List<ReminderDBO>>

    @Query("SELECT * FROM ReminderDBO WHERE uniqueKey = :key")
    suspend fun getReminderByKey(key: String): ReminderDBO

    @Query("DELETE FROM ReminderDBO WHERE uniqueKey = :key ")
    suspend fun removeReminder(key: String)
}