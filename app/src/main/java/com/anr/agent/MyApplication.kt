package com.anr.agent

import android.app.Application
import com.farimarwat.grizzly.GrizzlyMonitorBuilder

class MyApplication(
) : Application() {

    override fun onCreate() {
        super.onCreate()

        val firebaseCrashlytics = FirebaseCrashlytics.getInstance()

        // Initialize and start GrizzlyMonitor with custom settings
        GrizzlyMonitorBuilder(this)
            .withThreshold(3000L) // Set ANR threshold (1000-4500ms)
            .withTitle("App Error") // Set custom crash dialog title
            .withMessage("An error occurred. Please restart.") // Set custom crash dialog message
            .withFirebaseCrashLytics(firebaseCrashlytics) // Integrate with Firebase Crashlytics
            .build()
            .start()
    }

}