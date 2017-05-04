package com.prolificinteractive.simcoe.trackers

import com.prolificinteractive.simcoe.Properties
import com.prolificinteractive.simcoe.TrackingResult

/**
 * The page view tracking interface, used with analytics.
 */
interface PageViewTracking : AnalyticsTracking {

  /**
   * Tracks a page view.
   * @param pageName The page to track.
   * @param properties The optional properties.
   * @return A tracking result.
   */
  fun trackPageView(pageName: String, properties: Properties?): TrackingResult
}
