package com.anr.agent

import com.anr.agent.anr_detector.AnrMonitor

class AnrAgentMonitorImpl (
    override var anrMonitor: AnrMonitor
) :AnrAgentMonitor {
    override fun start() {
        crashMonitor.startMonitor()
        anrMonitor.startMonitor()
    }

    override fun stop() {
        crashMonitor.stopMonitor()
        anrMonitor.stopMonitor()
    }
}