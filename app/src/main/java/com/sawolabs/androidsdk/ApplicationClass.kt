package com.sawolabs.androidsdk

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.google.gson.Gson
import com.onesignal.OneSignal


//change the app id accordingly
//const val ONESIGNAL_APP_ID = "25f5431e-5523-4a96-8c6f-d6957424b19d"
const val ONESIGNAL_APP_ID = "198b67ad-4bbe-417a-b420-4c0e75e79ec2"

class ApplicationClass : Application() {
    val sharedPreferences: SharedPreferences = this.getSharedPreferences("OneSignal", Context.MODE_PRIVATE)

    val OnesignalAppId = sharedPreferences.getString("app_id","198b67ad-4bbe-417a-b420-4c0e75e79ec2")


    override fun onCreate() {
        super.onCreate()



        // Logging set to help debug issues, remove before releasing your app.
        // OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(OnesignalAppId.toString())

        OneSignal.setNotificationOpenedHandler { result ->
            val intent = Intent(this, NotificationActivity::class.java).apply {
                putExtra(
                    TRUSTED_DEVICE_NOTIFICATION_ADDITIONAL_DATA,
                    result.notification.additionalData.toString()
                )
                flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            this.startActivity(intent)
        }
    }
}



