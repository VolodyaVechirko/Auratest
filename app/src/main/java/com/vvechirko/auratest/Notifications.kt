package com.vvechirko.auratest

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.vvechirko.auratest.ui.MainActivity

const val CHANNEL_ID = "last_event"
const val CHANNEL_NAME = "Last device event"

@RequiresApi(Build.VERSION_CODES.O)
fun eventsChannel() =
    NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)

fun eventNotification(context: Context, body: String): Notification {
    val contentIntent = PendingIntent.getActivity(
        context,
        0,
        Intent(context, MainActivity::class.java),
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    return NotificationCompat.Builder(context, CHANNEL_ID)
        .setContentTitle(context.getString(R.string.app_name))
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setContentText(body)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setAutoCancel(true)
        .setContentIntent(contentIntent)
        .build()
}
