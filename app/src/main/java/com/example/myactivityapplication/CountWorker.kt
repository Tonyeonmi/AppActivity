package com.example.myactivityapplication

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Thread.sleep


const val Worker_NOTIFICATION_ID = 2
class CountWorker(val context: Context, workerParameters: WorkerParameters)
    : Worker(context, workerParameters) {
    override fun doWork(): Result {

        for( progress in 1..100) {
            sleep(300)
            Log.d("Worker", "progress = $progress")
        }

        val contentIntent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context,0,contentIntent,
            PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(context, App.ALERT_CHANNEL_ID)
            .setContentTitle("Count Alert")
            .setContentText("Count finished")
            .setSmallIcon(R.drawable.ic_baseline_heart_broken_24)
            .setContentIntent(pendingIntent)
            .build()

        context.getSystemService(NotificationManager::class.java)
            .notify(Worker_NOTIFICATION_ID, notification)

        return Result.success()

    }


}