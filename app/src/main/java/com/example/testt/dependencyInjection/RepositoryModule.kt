package com.example.testt.dependencyInjection

import com.example.testt.domain.repositories.ReminderRepository
import com.example.testt.domain.repositories.ReminderRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindReminderRepository(
        reminderRepository: ReminderRepository
    ):ReminderRepositoryInterface
}