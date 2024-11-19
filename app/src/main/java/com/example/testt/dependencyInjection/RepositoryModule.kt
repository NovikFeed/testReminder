package com.example.testt.dependencyInjection

import android.content.Context
import com.example.testt.domain.repositories.ReminderRepository
import com.example.testt.domain.repositories.ReminderRepositoryInterface
import com.example.testt.domain.repositories.WorkerRepository
import com.example.testt.domain.repositories.WorkerRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindReminderRepository(
        reminderRepository: ReminderRepository
    ): ReminderRepositoryInterface

    @Binds
    @Singleton
    abstract fun bindWorkerRepository(
        workerRepository: WorkerRepository
    ): WorkerRepositoryInterface
}

