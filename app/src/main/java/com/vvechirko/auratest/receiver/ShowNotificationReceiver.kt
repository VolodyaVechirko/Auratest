package com.vvechirko.auratest.receiver

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.vvechirko.auratest.R
import com.vvechirko.auratest.data.EventModel
import com.vvechirko.auratest.data.EventRepository
import com.vvechirko.auratest.eventNotification
import com.vvechirko.auratest.isGranted
import com.vvechirko.auratest.notificationManager
import com.vvechirko.auratest.preLast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@AndroidEntryPoint
class ShowNotificationReceiver : BroadcastReceiver() {

    @Inject
    lateinit var repository: EventRepository

    override fun onReceive(context: Context, intent: Intent) {
        CoroutineScope(Dispatchers.Main).launch {
            val events = repository.getAllEvents()
            showNotification(context, events)
        }
    }

    private fun showNotification(context: Context, events: List<EventModel>) {
        Log.d("App:showNotification", Date().toString())

        val body: String = when {
            events.isEmpty() -> context.getString(R.string.notification_no_events)
            events.size == 1 -> context.getString(
                R.string.notification_one_event,
                events.first().timestamp
            )

            else -> {
                val interval = events.last().timestamp - events.preLast().timestamp
                context.getString(R.string.notification_more_events, interval)
            }
        }

        if (context.isGranted(Manifest.permission.POST_NOTIFICATIONS)) {
            context.notificationManager()
                .notify(NOTIFICATION_ID, eventNotification(context, body))
        }
    }

    companion object {
        const val NOTIFICATION_ID = 123
    }
}
