package com.prolificinteractive.simcoe;

import android.content.Context;
import com.prolificinteractive.simcoe.trackers.ErrorLogging;
import com.prolificinteractive.simcoe.trackers.EventTracking;
import com.prolificinteractive.simcoe.trackers.LifetimeValueTracking;
import com.prolificinteractive.simcoe.trackers.PageViewTracking;
import com.prolificinteractive.simcoe.trackers.TimedEventTracking;
import com.prolificinteractive.simcoe.trackers.UserAttributeTracking;
import org.json.JSONObject;

/**
 * The empty provider.
 *
 * Used for logging analytics events when no other provider is specified.
 */
public class EmptyProvider implements ErrorLogging, EventTracking, LifetimeValueTracking,
    PageViewTracking, TimedEventTracking, UserAttributeTracking {

  private static final String EMPTY_PROVIDER_NAME = "Analytics";

  @Override public TrackingResult logError(
      final String error,
      final JSONObject properties) {
    return new TrackingResult(true, null);
  }

  @Override public String providerName() {
    return EMPTY_PROVIDER_NAME;
  }

  @Override public TrackingResult setUserAttribute(final String key, final Object value) {
    return new TrackingResult(true, null);
  }

  @Override public TrackingResult setUserAttributes(final JSONObject attributes) {
    return new TrackingResult(true, null);
  }

  @Override public void start(final Context context) { /* Nothing to do here. */ }

  @Override public TrackingResult startTimedEvent(
      final String eventName,
      final JSONObject properties) {
    return new TrackingResult(true, null);
  }

  @Override public void stop() {
    /* Nothing to do here. */
  }

  @Override public TrackingResult stopTimedEvent(
      final String eventName,
      final JSONObject properties) {
    return new TrackingResult(true, null);
  }

  @Override public TrackingResult trackEvent(final String eventName, final JSONObject properties) {
    return new TrackingResult(true, null);
  }

  @Override public TrackingResult trackLifetimeValue(final String key, final Object value) {
    return new TrackingResult(true, null);
  }

  @Override public TrackingResult trackLifetimeValues(final JSONObject values) {
    return new TrackingResult(true, null);
  }

  @Override public TrackingResult trackPageView(
      final String pageName,
      final JSONObject properties) {
    return new TrackingResult(true, null);
  }
}
