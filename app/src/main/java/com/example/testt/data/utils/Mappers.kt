package com.example.testt.data.utils

import com.example.testt.data.model.ReminderDBO
import com.example.testt.domain.model.Reminder

fun Reminder.toReminderDBO(): ReminderDBO {
    return ReminderDBO(
        uniqueKey = uniqueKey,
        message = message,
        day = day,
        minute = minute,
        hour = hour,
        month = month,
        year = year,
        title = title,
        repeat = repeat

    )
}
fun ReminderDBO.toReminder(): Reminder {
    return Reminder(
        uniqueKey = uniqueKey,
        message = message,
        day = day,
        minute = minute,
        hour = hour,
        month = month,
        year = year,
        title = title,
        repeat = repeat

    )
}