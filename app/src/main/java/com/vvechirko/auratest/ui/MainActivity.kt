package com.vvechirko.auratest.ui

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vvechirko.auratest.R
import com.vvechirko.auratest.data.EventModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var rootView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rootView = findViewById(R.id.root)

        mainViewModel.eventsData.observe(this, this::displayEvents)

        // TODO: ask user android.permission.POST_NOTIFICATIONS
    }

    private fun displayEvents(events: List<EventModel>) {
        rootView.text = if (events.isEmpty()) {
            getString(R.string.notification_no_events)
        } else {
            events.mapIndexed { index, event ->
                "$index - ${event.timestamp}"
            }.joinToString(separator = "\n")
        }
    }
}
