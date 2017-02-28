package com.prolificinteractive.simcoe.trackers;

import com.prolificinteractive.simcoe.TrackingResult;
import java.util.Map;

/**
 * The page view tracking interface, used with analytics.
 */
public interface PageViewTracking extends AnalyticsTracking {

  /**
   * Tracks a page view.
   *
   * @param pageName The page to track.
   * @param properties The optional properties.
   * @return A tracking result.
   */
  TrackingResult trackPageView(String pageName, Map<String, Object> properties);
}
