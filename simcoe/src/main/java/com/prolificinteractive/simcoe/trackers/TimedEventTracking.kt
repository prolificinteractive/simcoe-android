package com.prolificinteractive.simcoe.trackers

import com.prolificinteractive.simcoe.Properties
import com.prolificinteractive.simcoe.TrackingResult

/**
 * The timed event tracking interface, used with analytics.
 */
interface TimedEventTracking : AnalyticsTracking {

    /**
     * Starts tracking a timed event with a given provider.
     *
     * @param eventName The event name.
     * @param properties The associated properties.
     * @return The tracking result.
     */
    fun startTimedEvent(eventName: String, properties: Properties?): TrackingResult

    /**
     * Stops tracking a timed event with a given provider.
     *
     * @param eventName The event name.
     * @param properties The associated properties.
     * @return The tracking result.
     */
    fun stopTimedEvent(eventName: String, properties: Properties?): TrackingResult
}
