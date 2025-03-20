package com.anr.agent

import com.anr.agent.anr_detector.AnrMonitor

interface AnrAgentMonitor {
    var anrMonitor: AnrMonitor
    var crashMonitor:CrashMonitor
    fun start()
    fun stop()
}