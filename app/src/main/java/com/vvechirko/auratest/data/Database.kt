package com.vvechirko.auratest.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase

@Database(entities = [EventModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getEventsDao(): EventsDao
}

@Dao
interface EventsDao {
    @Insert // (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: EventModel)

    @Query("SELECT * FROM events")
    suspend fun getAll(): List<EventModel>
}
