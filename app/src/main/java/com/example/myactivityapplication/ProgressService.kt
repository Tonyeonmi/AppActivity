package com.example.myactivityapplication

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val PROGRESS_MAX = 100
const val SERVICE_ID = 1
class ProgressService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val contentIntent = Intent(this,MainActivity::class.java )
        val pendingIntent = PendingIntent.getActivity(this,
        0,
        contentIntent,
        PendingIntent.FLAG_IMMUTABLE
        )

        val notificationBuilder = NotificationCompat.Builder(this, App.PROGRESS_CHANNEL_ID)
            .setContentTitle("Service Test")
            .setContentText("Service is progress")
            .setSmallIcon(R.drawable.ic_baseline_heart_broken_24)
            .setContentIntent(pendingIntent)
            .setProgress(PROGRESS_MAX,0,false)

        startForeground(SERVICE_ID, notificationBuilder.build())

        GlobalScope.launch {
           val notiManger = getSystemService(NotificationManager::class.java)
            for( progress in 1 .. PROGRESS_MAX) {
                delay(200)

                notificationBuilder.setProgress(PROGRESS_MAX, progress, false)
                notiManger.notify(SERVICE_ID, notificationBuilder.build())
                Log.d("Service", "Progress = $progress")
            }

            stopForeground(true)
            stopSelf()

        }

        return START_NOT_STICKY // 종료되더라도 재실행되지 않도록 하겠다.
    }

    override fun onBind(p0: Intent?): IBinder? {
       return null
    }
}