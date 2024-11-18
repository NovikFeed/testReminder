package com.example.testt.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testt.data.model.ReminderDBO

@Database(
    entities = [ReminderDBO::class],
    version = 2
)
abstract class ReminderDatabase : RoomDatabase() {
    abstract val reminderDAO: ReminderDAO
}