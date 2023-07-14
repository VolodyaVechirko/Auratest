package com.vvechirko.auratest.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class EventModel(
    @PrimaryKey
    var timestamp: Int,
    var title: String
)