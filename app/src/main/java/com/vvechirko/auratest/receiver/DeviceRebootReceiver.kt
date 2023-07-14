package com.vvechirko.auratest.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.vvechirko.auratest.data.EventRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@AndroidEntryPoint
class DeviceRebootReceiver : BroadcastReceiver() {

    @Inject
    lateinit var repository: EventRepository

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("DeviceRebootReceiver", "onReceive: $intent")
        CoroutineScope(Main).launch {
            repository.saveEvent("Device restart", Date())
//            NotificationHandler.schedule(context)
        }
    }
}
