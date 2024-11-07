package com.example.testt.domain.model

import java.sql.Time
import java.util.Date

data class Reminder(
    val uniqueKey: String,
    val title: String,
    val message: String,
    val date: Date,
    val time : Time,
    val repeat : Int,

) {}