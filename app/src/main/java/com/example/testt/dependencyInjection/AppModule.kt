package com.example.testt.dependencyInjection

import android.app.Application
import androidx.room.Room
import com.example.testt.data.ReminderDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesDatabase(app: Application) : ReminderDatabase{
        return Room.databaseBuilder(
            app,
            ReminderDatabase::class.java,
            "reminder.db"
        ).build()
    }



}