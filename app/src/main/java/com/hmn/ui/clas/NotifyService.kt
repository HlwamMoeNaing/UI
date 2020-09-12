package com.hmn.ui.clas

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.hmn.ui.util.NotificationUtils

/// ** testing ***
class NotifyService(appContext: Context, workerParams: WorkerParameters):Worker(appContext,workerParams) {
    override fun doWork(): Result {
        NotificationUtils.createNotification(applicationContext, "Test", "Success")
        return Result.success()
    }
}

