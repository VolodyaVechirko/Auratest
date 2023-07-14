package com.vvechirko.auratest

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import com.vvechirko.auratest.receiver.ShowNotificationReceiver
import java.util.Date

object NotificationHandler {

    fun schedule(context: Context) {
        Log.d("App:schedule", Date().toString())
        val triggerTime = SystemClock.elapsedRealtime()
        context.alarmManager().setRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            triggerTime,
            AlarmManager.INTERVAL_FIFTEEN_MINUTES,
            alarmIntent(context)
        )
    }

    fun cancel(context: Context) {
        Log.d("App:cancel", Date().toString())
        context.alarmManager().cancel(alarmIntent(context))
    }

    private fun alarmIntent(context: Context) =
        PendingIntent.getBroadcast(
            context,
            1,
            Intent(context, ShowNotificationReceiver::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        )
}
