package com.hmn.ui.clas

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.hmn.ui.MainActivity
import com.hmn.ui.R
import com.hmn.ui.util.NotificationUtils

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
           val notiTitle = intent.getStringExtra("pilltitle")!!

    NotificationUtils.createNotification(context,notiTitle,"This is Noti")

        Log.e("brocast",notiTitle)

    }
}