package com.example.testt.domain.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.testt.R
import com.example.testt.data.ReminderDatabase
import com.example.testt.domain.repositories.ReminderRepository
import com.example.testt.domain.repositories.WorkerRepository
import com.example.testt.ui.MainActivity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltWorker
class WorkerManager @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted private val params: WorkerParameters,
    private val repository: ReminderRepository,
    private val workerRepository: WorkerRepository
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val reminderKey = params.inputData.getString("key")!!
        val reminderTitle = params.inputData.getString("title")!!
        val repeat = params.inputData.getString("repeat")!!
        Log.d("codrd", "TaskComplite")
        sendNotification(reminderTitle)
        if (repeat == "Once") {
            repository.removeReminder(reminderKey)
        } else {
            workerRepository.addTaskToWorkerWithRepeat(reminderKey, reminderTitle, repeat)
        }
        return Result.success()

    }

    private fun sendNotification(title: String?) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        val channel = NotificationChannel(
            "REMINDER_IDI",
            "Reminder message",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "reminders"
            enableLights(true)
            lightColor = Color.RED
            enableVibration(true)
        }
        notificationManager.createNotificationChannel(channel)
        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            Intent(applicationContext, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(applicationContext, "REMINDER_IDI")
            .setContentTitle(title)
            .setContentText("Hurry up! Don’t forget your task :)")
            .setSmallIcon(R.drawable.icon)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setFullScreenIntent(pendingIntent, true)
            .build()

        notificationManager.notify(1, notification)
    }


}