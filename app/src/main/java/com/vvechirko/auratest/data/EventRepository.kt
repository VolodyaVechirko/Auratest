package com.vvechirko.auratest.data

import java.util.Date
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val eventsDao: EventsDao
) {
    suspend fun saveEvent(name: String, date: Date) {
        val event = EventModel(date.time.toInt(), name)
        eventsDao.insert(event)
    }

    suspend fun getAllEvents(): List<EventModel> {
        return eventsDao.getAll()
    }
}