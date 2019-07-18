package com.architecture.www.xmlshowcase

import android.app.Application
import android.os.StrictMode
import com.bugsnag.android.Bugsnag

/** @author Johnett Mathew */
open class XmlShowCaseApplication : Application() {
//    val leakedViews = mutableListOf<View>()
    override fun onCreate() {
        super.onCreate()
        Bugsnag.init(this)
        enabledStrictMode()
    }

    private fun enabledStrictMode() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDeath()
                .build()
        )
    }
}