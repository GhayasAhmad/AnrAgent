package com.anr.agent

import android.content.Context
import com.anr.agent.anr_detector.AnrMonitorBuilder
import com.anr.agent.anr_detector.AnrMonitorBuilder.THRESHOLD_DEFAULT


/**
 * Builder class for creating an instance of [GrizzlyMonitor].
 *
 * @property context The application context.
 */
class AnrAgentBuilder(private val context: Context) {

    // ANR Monitor Params
    private var mThreshold: Long = THRESHOLD_DEFAULT

    // Crash Monitor Params
    private var mTitle: String = "Application Error"
    private var mMessage: String = "An unexpected error occurred. Please restart the app."
    private var mFirebaseCrashlytics: FirebaseCrashlytics? = null

    /**
     * Sets the threshold for detecting ANRs.
     *
     * @param threshold The threshold in milliseconds. Valid values are between 1000 and 4500.
     * @return The current instance of [GrizzlyMonitorBuilder].
     */
    fun withThreshold(threshold: Long): GrizzlyMonitorBuilder {
        this.mThreshold = threshold
        return this
    }

    /**
     * Sets the title for crash dialogs.
     *
     * @param title The title to be displayed.
     * @return The current instance of [GrizzlyMonitorBuilder].
     */
    fun withTitle(title: String): GrizzlyMonitorBuilder {
        this.mTitle = title
        return this
    }

    /**
     * Sets the message for crash dialogs.
     *
     * @param message The message to be displayed.
     * @return The current instance of [GrizzlyMonitorBuilder].
     */
    fun withMessage(message: String): GrizzlyMonitorBuilder {
        this.mMessage = message
        return this
    }

    /**
     * Sets the Firebase Crashlytics instance for crash reporting.
     *
     * @param firebaseCrashlytics The FirebaseCrashlytics instance.
     * @return The current instance of [GrizzlyMonitorBuilder].
     */
    fun withFirebaseCrashLytics(firebaseCrashlytics: FirebaseCrashlytics): GrizzlyMonitorBuilder {
        this.mFirebaseCrashlytics = firebaseCrashlytics
        return this
    }


    /**
     * Builds and returns an instance of [GrizzlyMonitor].
     *
     * @return A new instance of [GrizzlyMonitor].
     */
    fun build(): GrizzlyMonitor {
        return GrizzlyMonitorImpl(
            AnrMonitorBuilder
                .withThreshold(mThreshold)
                .build(),
            CrashMonitorBuilder(context)
                .withTitle(mTitle)
                .withMessage(mMessage)
                .withFirebaseCrashlytics(mFirebaseCrashlytics)
                .build()
        )
    }
}