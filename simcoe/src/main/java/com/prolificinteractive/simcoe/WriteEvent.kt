package com.prolificinteractive.simcoe

import com.prolificinteractive.simcoe.trackers.AnalyticsTracking

/**
 * The write event.
 *
 * @param provider The analytics tracking provider.
 * @param trackingResult The tracking result.
 */
internal class WriteEvent(
    val provider: AnalyticsTracking,
    val trackingResult: TrackingResult
)
