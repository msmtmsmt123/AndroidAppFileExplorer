package me.izzp.androidappfileexplorer

import android.app.Activity
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v7.app.NotificationCompat

/**
 * Created by zzp on 2017-08-08.
 */
object AppFileExplorer {

    val customDirs = ArrayList<String>()

    /**
     * 增加一些需要显示的目录
     */
    @JvmStatic
    fun addDirs(dirs: List<String>) {
        customDirs += dirs
    }

    /**
     * 打开文件浏览器
     */
    @JvmStatic
    fun open(context: Context) {
        val intent = Intent(context, DirListActivity::class.java)
        if (context !is Activity) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    /**
     * 在通知栏显示入口
     */
    @JvmStatic
    fun showNotification(context: Context) {
        val context = context.applicationContext
        val intent = Intent(context, DirListActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val noti = NotificationCompat.Builder(context)
                .setContentTitle("点击打开FileExplorer")
                .setOngoing(true)
                .setDefaults(0)
                .setAutoCancel(false)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentIntent(pi)
                .build()
        val mgr = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mgr.notify(R.id.afe_noti, noti)
    }

    /**
     * 取消通知栏入口
     */
    @JvmStatic
    fun dismissNotification(context: Context) {
        val mgr = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mgr.cancel(R.id.afe_noti)
    }
}