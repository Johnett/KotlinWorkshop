package com.architecture.www.xmlshowcase

import android.app.Application
import android.os.StrictMode
import android.view.View
import com.bugsnag.android.Bugsnag
import leakcanary.LeakCanary

open class XmlShowCaseApplication : Application() {
    val leakedViews = mutableListOf<View>()
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