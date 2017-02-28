package com.prolificinteractive.simcoe.trackers;

import com.prolificinteractive.simcoe.TrackingResult;
import java.util.Map;

/**
 * The lifetime value tracking interface, used with analytics.
 */
public interface LifetimeValueTracking extends AnalyticsTracking {

  /**
   * Tracks the lifetime values for a given key.
   *
   * @param key The lifetime value's identifier.
   * @param value The lifetime value.
   * @return A tracking result.
   */
  TrackingResult trackLifetimeValue(String key, Object value);

  /**
   * Tracks the lifetime values for a given JSON object.
   *
   * @param values The values.
   * @return A tracking result.
   */
  TrackingResult trackLifetimeValues(Map<String, Object> values);
}
