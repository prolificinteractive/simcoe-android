package com.prolificinteractive.sample;

import android.content.Context;
import com.prolificinteractive.simcoe.Simcoe;
import com.prolificinteractive.simcoe.Tracker;
import com.prolificinteractive.simcoe.trackers.AnalyticsTracking;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * The analytics helper.
 */
public class SampleAnalyticsHelper {

  private final Set<AnalyticsTracking> providers;
  private final Simcoe simcoe;

  /**
   * Creates an analytics helper instance.
   *
   * @param providers The analytics providers.
   */
  public SampleAnalyticsHelper(final Set<AnalyticsTracking> providers) {
    this.providers = providers;

    final TimberOutput timberOutput1 = new TimberOutput();
    final TimberOutput timberOutput2 = new TimberOutput();

    simcoe = new Simcoe(new Tracker(timberOutput1, timberOutput2));
  }

  /**
   * Sets a user attribute.
   *
   * @param key The key.
   * @param value The value.
   */
  public void setUserAttribute(final String key, final Object value) {
    simcoe.setUserAttribute(key, value);
  }

  /**
   * Sets multiple user attributes.
   *
   * @param attributes The attributes to log.
   */
  public void setUserAttributes(final Map<String, Object> attributes) {
    simcoe.setUserAttributes(attributes);
  }

  /**
   * Starts the analytics instance, with the analytics providers.
   *
   * @param context The context.
   */
  public void start(final Context context) {
    simcoe.run(context, new ArrayList<>(providers));
  }

  /**
   * Starts a timed event.
   *
   * @param eventName The name of the timed event.
   */
  public void startTimedEvent(final String eventName) {
    simcoe.startTimedEvent(eventName);
  }

  /**
   * Starts a timed event.
   *
   * @param eventName The name of the timed event.
   * @param properties The optional properties.
   */
  public void startTimedEvent(final String eventName, final Map<String, Object> properties) {
    simcoe.startTimedEvent(eventName, properties);
  }

  /**
   * Stops the analytics instance.
   */
  public void stop() {
    simcoe.stop();
  }

  /**
   * Stops a timed event.
   *
   * @param eventName The event name.
   */
  public void stopTimedEvent(final String eventName) {
    simcoe.stopTimedEvent(eventName);
  }

  /**
   * Stops the timed event.
   *
   * @param eventName The name of the timed event.
   * @param properties The optional properties.
   */
  public void stopTimedEvent(final String eventName, final Map<String, Object> properties) {
    simcoe.stopTimedEvent(eventName, properties);
  }

  /**
   * Tracks an analytics event.
   *
   * @param eventName The event name.
   */
  public void trackEvent(final String eventName) {
    simcoe.trackEvent(eventName);
  }

  /**
   * Tracks an analytics event.
   *
   * @param eventName The event name.
   * @param properties The associated properties.
   */
  public void trackEvent(final String eventName, final Map<String, Object> properties) {
    simcoe.trackEvent(eventName, properties);
  }

  /**
   * Tracks the lifetime values for a given JSON object.
   *
   * @param values The values.
   */
  public void trackLifetimeValues(final Map<String, Object> values) {
    simcoe.trackLifetimeValues(values);
  }

  /**
   * Tracks a page view.
   *
   * @param pageName The page to track.
   */
  public void trackPageView(final String pageName) {
    simcoe.trackPageView(pageName);
  }

  /**
   * Tracks a page view.
   *
   * @param pageName The page to track.
   * @param properties The optional properties.
   */
  public void trackPageView(final String pageName, final Map<String, Object> properties) {
    simcoe.trackPageView(pageName, properties);
  }
}
