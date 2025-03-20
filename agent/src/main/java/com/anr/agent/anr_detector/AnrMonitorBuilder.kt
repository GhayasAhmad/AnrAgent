package com.anr.agent.anr_detector

internal object AnrMonitorBuilder {
    val TICKER_DEFAULT = 100L
    val THRESHOLD_DEFAULT = 4000L

    private var mThreshold: Long = THRESHOLD_DEFAULT

    fun withThreshold(threshold: Long): AnrMonitorBuilder {
        mThreshold = threshold.coerceIn(1000, 4900)
        return this
    }

    fun build(): AnrMonitorImpl = AnrMonitorImpl(mThreshold)

}
