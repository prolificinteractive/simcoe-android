package com.prolificinteractive.simcoe.trackers;

import com.prolificinteractive.simcoe.TrackingResult;
import java.util.Map;

/**
 * The timed event tracking interface, used with analytics.
 */
public interface TimedEventTracking extends AnalyticsTracking {

  /**
   * Starts tracking a timed event with a given provider.
   *
   * @param eventName The event name.
   * @param properties The associated properties.
   * @return The tracking result.
   */
  TrackingResult startTimedEvent(String eventName, Map<String, Object> properties);

  /**
   * Stops tracking a timed event with a given provider.
   *
   * @param eventName The event name.
   * @param properties The associated properties.
   * @return The tracking result.
   */
  TrackingResult stopTimedEvent(String eventName, Map<String, Object> properties);
}