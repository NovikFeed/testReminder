package com.example.testt.domain.repositories

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.testt.domain.worker.WorkerManager
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WorkerRepository @Inject constructor(
    @ApplicationContext private val context: Context
) : WorkerRepositoryInterface {
    override suspend fun addTaskToWorker(
        key: String,
        title: String,
        triggerTime: LocalDateTime,
        repeat: String
    ) {
        val triggerTimeMillis = triggerTime
            .atZone(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()
        val currentTime = System.currentTimeMillis()
        val delayMillis = triggerTimeMillis - currentTime
        val delay = if (delayMillis > 0) delayMillis else 1
        Log.d("codrd", delay.toString())
        val dataForReminder = Data.Builder()
            .putString("title", title)
            .putString("key", key)
            .putString("repeat", repeat).build()
        val notificationForWorker = OneTimeWorkRequest.Builder(WorkerManager::class.java)
            .setInputData(dataForReminder)
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .build()
        try {
            WorkManager.getInstance(context).enqueueUniqueWork(
                key,
                ExistingWorkPolicy.REPLACE,
                notificationForWorker)

        }
        catch (e : Exception){
            Log.d("codr", e.message.toString())
        }



    }

    override suspend fun addTaskToWorkerWithRepeat(key: String, title: String, repeat: String) {
        val dataForReminder = Data.Builder()
            .putString("title", title)
            .putString("key", key)
            .putString("repeat", repeat).build()
        val repeatDay = if(repeat == "Daily") 1.toLong() else 7.toLong()
        val notificationForWorker = PeriodicWorkRequest.Builder(
            WorkerManager::class.java,
            repeatDay, TimeUnit.DAYS
        ).build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            key,
            ExistingPeriodicWorkPolicy.UPDATE,
            notificationForWorker
        )
    }

    override suspend fun removeTaskFromWorker(key : String) {
        WorkManager.getInstance(context).cancelUniqueWork(key)
    }
}