package com.hmn.ui.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.hmn.ui.MainActivity
import com.hmn.ui.R


class NotificationUtils {

    companion object {

//        fun notifyCustomMsg(context: Context, title: String, message: String) {
//            // Supporting both unicode & zawgyi
//            // String mmMessage = MMBindFontUtils.mmTextUnicodeOrigin(message);
//
//
//            //Message in BigText Style
//            val bigTextStyle = NotificationCompat.BigTextStyle()
//            bigTextStyle.bigText(message)
//
//            var smallIcon = R.mipmap.ic_launcher
//
//            val builder = NotificationCompat.Builder(context)
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                builder.setSmallIcon(smallIcon)
//                //            builder.setColor(context.getResources().getColor(R.color.kbz_blue));
//            } else {
//                builder.setSmallIcon(smallIcon)
//            }
//            val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//            builder.setContentTitle(title)
//                .setContentText(message)
//                .setAutoCancel(true)
//                .setStyle(bigTextStyle)
//                .setSound(alarmSound)
//                .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
//
//            //Open the app when user tap on notification
//            val notiIntent = Intent(context, MainActivity::class.java)
//            notiIntent.addCategory(Intent.CATEGORY_HOME)
//            notiIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            notiIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
//            val pendingIntent = PendingIntent.getActivity(
//                context, System.currentTimeMillis().toInt(), notiIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT
//            )
//
//            builder.setContentIntent(pendingIntent)
//
//            // Notify to the System
//            val notificationManager = NotificationManagerCompat.from(context)
//            notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
//        }

        private var mNotificationManager: NotificationManager? = null
        private var mBuilder: NotificationCompat.Builder? = null
        // private const val NOTIFICATION_CHANNEL_ID = "10001"

        fun createNotification(context: Context, title: String, message: String) {
            /**Creates an explicit intent for an Activity in your app */
            val resultIntent = Intent(context, MainActivity::class.java)
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            val resultPendingIntent = PendingIntent.getActivity(
                context,
                0 /* Request code */, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            var smallIcon = R.mipmap.ic_launcher

            var channelID = (0..1000).random().toString()
            mBuilder = NotificationCompat.Builder(context, channelID)
            mBuilder!!.setSmallIcon(getNotificationIcon())
            mBuilder!!.setStyle(NotificationCompat.BigTextStyle().bigText(message))
            mBuilder!!.setLargeIcon(
                BitmapFactory.decodeResource(
                    context.resources,
                    smallIcon
                )
            )
            mBuilder!!.setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))//Settings.System.DEFAULT_NOTIFICATION_URI)
                .setContentIntent(resultPendingIntent)


            mNotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val notificationChannel = NotificationChannel(
                    channelID,
                    "NOTIFICATION_CHANNEL_NAME",
                    importance
                )
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.RED
                notificationChannel.enableVibration(true)
                notificationChannel.vibrationPattern =
                    longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
                assert(mNotificationManager != null)
                mBuilder!!.setChannelId(channelID)
                mNotificationManager!!.createNotificationChannel(notificationChannel)
            }
            assert(mNotificationManager != null)
            mNotificationManager!!.notify(channelID.toInt() /* Request Code */, mBuilder!!.build())
        }

        private fun getNotificationIcon(): Int {
            val useWhiteIcon =
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
            return if (useWhiteIcon) R.mipmap.ic_launcher else R.mipmap.ic_launcher
        }
    }


}