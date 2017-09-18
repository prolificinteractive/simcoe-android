package com.prolificinteractive.simcoe.trackers

import com.prolificinteractive.simcoe.Properties
import com.prolificinteractive.simcoe.TrackingResult

/**
 * The lifetime value tracking interface, used with analytics.
 */
interface LifetimeValueTracking : AnalyticsTracking {

  /**
   * Tracks the lifetime values for a given key.
   *
   * @param key The lifetime value's identifier.
   * @param value The lifetime value.
   * @return A tracking result.
   */
  fun trackLifetimeValue(key: String, value: Any, properties: Properties?): TrackingResult

  /**
   * Tracks the lifetime values for a given JSON object.
   *
   * @param values The values.
   * @return A tracking result.
   */
  fun trackLifetimeValues(attributes : Properties, values: Properties?): TrackingResult
}
