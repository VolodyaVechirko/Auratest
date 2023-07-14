package com.vvechirko.auratest

import android.app.AlarmManager
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat.checkSelfPermission

fun Context.notificationManager(): NotificationManager =
    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

fun Context.alarmManager(): AlarmManager =
    getSystemService(Context.ALARM_SERVICE) as AlarmManager

fun Context.isGranted(permission: String) =
    checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED


fun <T> List<T>.preLast(): T {
    if (size < 2)
        throw NoSuchElementException("List has less than two elements")
    return this[size - 2]
}
