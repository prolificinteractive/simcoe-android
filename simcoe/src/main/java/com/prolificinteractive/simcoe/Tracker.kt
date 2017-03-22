package com.prolificinteractive.simcoe

import com.prolificinteractive.simcoe.trackers.AnalyticsTracking
import java.util.ArrayList

/**
 * A recorder for SimcoeEvents.
 * @param outputOption The output options.
 * @param errorOption  The error options.
 */
class Tracker @JvmOverloads constructor(
        var outputOption: OutputOptions = OutputOptions.VERBOSE,
        var errorOption: ErrorHandlingOption = ErrorHandlingOption.DEFAULT,
        vararg outputs: Output) {

    constructor() : this(outputs = ConsoleOutput())

    private val outputSources = outputs

    companion object {
        const private val ANALYTICS_PROVIDER_NAME = "Analytics"
        const private val ANALYTICS_ERROR = "** ANALYTICS ERROR **"
    }

    /**
     * Records the event.
     *
     * @param event The event to record.
     * @throws Exception The exception thrown when ErrorHandlingOption.STRICT is used.
     */
    internal fun track(event: Event) {
        val successfulProviders = ArrayList<AnalyticsTracking>()

        for (writeEvent in event.writeEvents) {
            if (!writeEvent.trackingResult.isError) {
                successfulProviders.add(writeEvent.provider)
            } else {
                handleError(writeEvent.trackingResult.errorMessage)
                return
            }
        }

        write(event.description, successfulProviders)
    }

    @Throws(SimcoeException::class)
    private fun handleError(message: String?) {
        when (errorOption) {
            ErrorHandlingOption.DEFAULT -> outputSources.writeOut(ANALYTICS_ERROR, message ?: "Unknown error")
            ErrorHandlingOption.SUPPRESS -> { }
            ErrorHandlingOption.STRICT -> throw SimcoeException(message)
        }
    }

    private fun write(description: String, providers: List<AnalyticsTracking>) {
        when (outputOption) {
            OutputOptions.NONE -> return
            OutputOptions.VERBOSE -> providers.forEach { outputSources.writeOut(it.providerName(), description) }
            OutputOptions.SIMPLE -> outputSources.writeOut(ANALYTICS_PROVIDER_NAME, description)
        }
    }
}

