package com.example.testt.domain.model

import java.sql.Time
import java.util.Date

data class Reminder(
    val uniqueKey: String,
    val title: String,
    val message: String,
    val day: Int,
    val month : Int,
    val year : Int,
    val hour : Int,
    val minute : Int,
    val repeat : String,

) {}