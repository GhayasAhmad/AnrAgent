package com.anr.agent.anr_detector

import android.os.Handler
import android.os.Looper
import android.util.Log
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


private const val TAG = "ANR_MONITOR"

internal class AnrMonitorImpl(
    private val mThreshold: Long = 4000L
) : AnrMonitor {

    private val mainHandler = Handler(Looper.getMainLooper())
    private val watchdogExecutor = Executors.newSingleThreadScheduledExecutor()
    @Volatile private var lastUpdateTime: Long = System.currentTimeMillis()
    @Volatile private var running = false

    override fun startMonitor() {
        if (running) return
        running = true

        // Runnable posted to the main thread to track responsiveness
        val mainThreadRunnable = object : Runnable {
            override fun run() {
                lastUpdateTime = System.currentTimeMillis()
                if (running) {
                    mainHandler.postDelayed(this, 1000) // Refresh every second
                }
            }
        }

        // Start tracking main thread responsiveness
        mainHandler.post(mainThreadRunnable)

        // Watchdog thread checks if the main thread is stuck
        watchdogExecutor.scheduleWithFixedDelay({
            val currentTime = System.currentTimeMillis()
            val elapsedTime = currentTime - lastUpdateTime

            if (elapsedTime >= mThreshold) {
                val stackTrace = Looper.getMainLooper().thread.stackTrace
                handleAnr(stackTrace, elapsedTime)
            }
        }, 0, 500, TimeUnit.MILLISECONDS) // Check every 500ms
    }

    override fun stopMonitor() {
        running = false
        watchdogExecutor.shutdownNow()
    }

    override fun handleAnr(stack: Array<StackTraceElement>, duration: Long) {
        val method = stack.firstOrNull()?.methodName ?: "Unknown"
        val title = "ANR detected: Method `$method` blocked main thread for at least $duration ms"
        val exception = AnrMonitorException(title, stack)

        Log.i(TAG, exception.toString())

        throw exception
    }
}