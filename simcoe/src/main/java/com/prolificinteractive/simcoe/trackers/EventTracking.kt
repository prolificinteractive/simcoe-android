package com.prolificinteractive.simcoe.trackers

import com.prolificinteractive.simcoe.Properties
import com.prolificinteractive.simcoe.TrackingResult

/**
 * The event tracking interface, used with analytics.
 */
interface EventTracking : AnalyticsTracking {

  /**
   * Tracks an event with a given provider.
   *
   * @param eventName The event name.
   * @param properties The associated properties.
   * @return The tracking result.
   */
  fun trackEvent(eventName: String, properties: Properties?): TrackingResult
}
