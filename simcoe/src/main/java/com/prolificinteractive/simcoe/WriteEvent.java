package com.prolificinteractive.simcoe;

import com.prolificinteractive.simcoe.trackers.AnalyticsTracking;

/**
 * The write event.
 */
class WriteEvent {

  /**
   * The analytics tracking provider.
   */
  final AnalyticsTracking provider;

  /**
   * The tracking result.
   */
  final TrackingResult trackingResult;

  /**
   * Creates a write event instance.
   *
   * @param provider The analytics tracking provider.
   * @param trackingResult The tracking result.
   */
  protected WriteEvent(final AnalyticsTracking provider, final TrackingResult trackingResult) {
    this.provider = provider;
    this.trackingResult = trackingResult;
  }
}
