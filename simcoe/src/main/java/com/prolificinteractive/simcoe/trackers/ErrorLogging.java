package com.prolificinteractive.simcoe.trackers;

import com.prolificinteractive.simcoe.TrackingResult;
import org.json.JSONObject;

/**
 * Defines methods for logging errors to analytics.
 */
public interface ErrorLogging extends AnalyticsTracking {

  /**
   * Logs the error with additional properties.
   *
   * @param error The error to log.
   * @param properties The additional properties.
   * @return The tracking result.
   */
  TrackingResult logError(String error, JSONObject properties);
}
