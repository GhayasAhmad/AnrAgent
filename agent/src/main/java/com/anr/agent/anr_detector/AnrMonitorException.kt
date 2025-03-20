package com.anr.agent.anr_detector

internal class AnrMonitorException(
    title: String,
    stacktrace: Array<StackTraceElement>
) :
    Throwable(title) {
    init {
        stackTrace = stacktrace
    }
}