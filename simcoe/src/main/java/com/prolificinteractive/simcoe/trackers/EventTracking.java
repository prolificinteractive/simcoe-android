package com.prolificinteractive.simcoe.trackers;

import com.prolificinteractive.simcoe.TrackingResult;
import java.util.Map;

/**
 * The event tracking interface, used with analytics.
 */
public interface EventTracking extends AnalyticsTracking {

  /**
   * Tracks an event with a given provider.
   *
   * @param eventName The event name.
   * @param properties The associated properties.
   * @return The tracking result.
   */
  TrackingResult trackEvent(String eventName, Map<String, Object> properties);
}
