package com.vvechirko.auratest.di

import android.app.Application
import androidx.room.Room
import com.vvechirko.auratest.data.AppDatabase
import com.vvechirko.auratest.data.EventsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "app_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideEventsDao(db: AppDatabase): EventsDao {
        return db.getEventsDao()
    }
}
