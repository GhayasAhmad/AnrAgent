package com.anr.agent

import com.anr.agent.anr_detector.AnrMonitor

interface AnrAgentMonitor {
    var anrMonitor: AnrMonitor
    fun start()
    fun stop()
}