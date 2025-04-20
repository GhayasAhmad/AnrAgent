package com.anr.agent

import com.anr.agent.anr_detector.AnrMonitor

class AnrAgentMonitorImpl (
    override var anrMonitor: AnrMonitor
) :AnrAgentMonitor {
    override fun start() {
        anrMonitor.startMonitor()
    }

    override fun stop() {
        anrMonitor.stopMonitor()
    }
}