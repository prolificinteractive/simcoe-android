package com.prolificinteractive.simcoe.trackers

import com.prolificinteractive.simcoe.Properties
import com.prolificinteractive.simcoe.TrackingResult

/**
 * Defines methods for logging errors to analytics.
 */
interface ErrorLogging : AnalyticsTracking {

  /**
   * Logs the error with additional properties.
   *
   * @param error The error to log.
   * @param properties The additional properties.
   *
   * @return The tracking result.
   */
  fun logError(error: String, properties: Properties?): TrackingResult
}
