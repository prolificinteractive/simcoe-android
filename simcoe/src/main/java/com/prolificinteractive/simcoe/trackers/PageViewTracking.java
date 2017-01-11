package com.prolificinteractive.simcoe.trackers;

import com.prolificinteractive.simcoe.TrackingResult;
import org.json.JSONObject;

/**
 * Created by Michael Campbell on 12/23/16.
 */

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
  TrackingResult trackPageView(String pageName, JSONObject properties);
}
