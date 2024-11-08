package com.mohaberabi.kmp.katch

import android.app.Application

class KatchApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
            CrashReporter.report(throwable)
        }
    }
}