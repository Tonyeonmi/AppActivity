package com.example.myactivityapplication

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Build.VERSION_CODES

class App : Application() {
    companion object {
        const val  PROGRESS_CHANNEL_ID = "com.example.myprogressnotification"
        const val ALERT_CHANNEL_ID = "com.example.myalertnotification"
    }

    override fun onCreate() {
        super.onCreate()

        if( Build.VERSION.SDK_INT >= VERSION_CODES .O ) {
            getSystemService(NotificationManager::class.java).run {
                val progressChannel = NotificationChannel(
                    PROGRESS_CHANNEL_ID,
                    "Progress Tests",
                    NotificationManager.IMPORTANCE_LOW
                )
                createNotificationChannel(progressChannel)

                val alertChannel = NotificationChannel(
                    ALERT_CHANNEL_ID,
                    "Alert Tests",
                    NotificationManager.IMPORTANCE_HIGH
                )
                createNotificationChannel(alertChannel)
            }
            }
        }
    }
