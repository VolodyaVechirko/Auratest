package com.vvechirko.auratest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vvechirko.auratest.data.EventModel
import com.vvechirko.auratest.data.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: EventRepository
) : ViewModel() {

    val eventsData: LiveData<List<EventModel>> = liveData {
        emit(repository.getAllEvents())
    }
}