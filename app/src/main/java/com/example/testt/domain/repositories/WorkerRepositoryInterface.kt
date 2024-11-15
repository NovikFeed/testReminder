package com.example.testt.domain.repositories

import java.time.LocalDateTime

interface WorkerRepositoryInterface {
    suspend fun addTaskToWorker(key: String,title : String, triggerTime : LocalDateTime, repeat : String)
    suspend fun addTaskToWorkerWithRepeat(key: String,title : String, repeat : String)
    suspend fun removeTaskFromWorker(key : String)
}
