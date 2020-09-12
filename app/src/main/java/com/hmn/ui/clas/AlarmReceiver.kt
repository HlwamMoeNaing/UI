package com.hmn.ui.clas

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.hmn.ui.util.NotificationUtils

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(cxt: Context, intent: Intent) {

        NotificationUtils.createNotification(cxt,"titl","dfsdfasdsadf")
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {

        }



    }
}