package com.anr.agent.anr_detector

interface AnrMonitor {
    fun handleAnr(stack: Array<StackTraceElement>, duration: Long)
    fun startMonitor()
    fun stopMonitor()
}