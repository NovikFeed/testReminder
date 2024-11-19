package com.example.testt.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.util.Date

@Entity
data class ReminderDBO(
    @PrimaryKey
    val uniqueKey: String,
    val title: String,
    val day: Int,
    val month: Int,
    val year: Int,
    val hour: Int,
    val minute: Int,
    val repeat: String,
) {}