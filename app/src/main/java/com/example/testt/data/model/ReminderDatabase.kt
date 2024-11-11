package com.example.testt.data.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ReminderDBO::class],
    version = 1
)
abstract class ReminderDatabase : RoomDatabase() {
    abstract val reminderDAO: ReminderDAO
}