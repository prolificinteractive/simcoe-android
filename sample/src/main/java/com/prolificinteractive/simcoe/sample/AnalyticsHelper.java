package com.prolificinteractive.simcoe.sample;

import android.content.Context;
import com.prolificinteractive.simcoe.Simcoe;
import com.prolificinteractive.simcoe.trackers.AnalyticsTracking;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import timber.log.Timber;

/**
 * The analytics helper.
 */
public class AnalyticsHelper {

  private final Set<AnalyticsTracking> providers;

  /**
   * Creates an analytics helper instance.
   *
   * @param providers The analytics providers.
   */
  public AnalyticsHelper(final Set<AnalyticsTracking> providers) {
    this.providers = providers;
  }

  /**
   * Sets a user attribute.
   *
   * @param key The key.
   * @param value The value.
   */
  public void setUserAttribute(final String key, final Object value) {
    try {
      Simcoe.setUserAttribute(key, value);
    } catch (final Exception e) {
      Timber.e(e, "setUserAttribute");
    }
  }

  /**
   * Sets multiple user attributes.
   *
   * @param attributes The attributes to log.
   */
  public void setUserAttributes(final Map<String, Object> attributes) {
    try {
      Simcoe.setUserAttributes(attributes);
    } catch (final Exception e) {
      Timber.e(e, "setUserAttributes");
    }
  }

  /**
   * Starts the analytics engine, with the analytics providers.
   *
   * @param context The context.
   */
  public void start(final Context context) {
    Simcoe.run(context, new ArrayList<>(providers));
  }

  /**
   * Starts a timed event.
   *
   * @param eventName The name of the timed event.
   */
  public void startTimedEvent(final String eventName) {
    startTimedEvent(eventName, null);
  }

  /**
   * Starts a timed event.
   *
   * @param eventName The name of the timed event.
   * @param properties The optional properties.
   */
  public void startTimedEvent(final String eventName, final Map<String, Object> properties) {
    try {
      Simcoe.startTimedEvent(eventName, properties);
    } catch (final Exception e) {
      Timber.e(e, "startTimedEvent");
    }
  }

  /**
   * Stops the analytics engine.
   */
  public void stop() {
    Simcoe.stop();
  }

  /**
   * Stops a timed event.
   *
   * @param eventName The event name.
   */
  public void stopTimedEvent(final String eventName) {
    stopTimedEvent(eventName, null);
  }

  /**
   * Stops the timed event.
   *
   * @param eventName The name of the timed event.
   * @param properties The optional properties.
   */
  public void stopTimedEvent(final String eventName, final Map<String, Object> properties) {
    try {
      Simcoe.stopTimedEvent(eventName, properties);
    } catch (final Exception e) {
      Timber.e(e, "stopTimedEvent");
    }
  }

  /**
   * Tracks an analytics event.
   *
   * @param eventName The event name.
   */
  public void trackEvent(final String eventName) {
    trackEvent(eventName, null);
  }

  /**
   * Tracks an analytics event.
   *
   * @param eventName The event name.
   * @param properties The associated properties.
   */
  public void trackEvent(final String eventName, final Map<String, Object> properties) {
    try {
      Simcoe.trackEvent(eventName, properties);
    } catch (final Exception e) {
      Timber.e(e, "error tracking event");
    }
  }

  /**
   * Tracks the lifetime values for a given JSON object.
   *
   * @param values The values.
   */
  public void trackLifetimeValues(final Map<String, Object> values) {
    try {
      Simcoe.trackLifetimeValues(values);
    } catch (final Exception e) {
      Timber.e(e, "trackLifetimeValues");
    }
  }

  /**
   * Tracks a page view.
   *
   * @param pageName The page to track.
   * @param properties The optional properties.
   */
  public void trackPageView(final String pageName, final Map<String, Object> properties) {
    try {
      Simcoe.trackPageView(pageName, properties);
    } catch (final Exception e) {
      Timber.e(e, "error tracking page view");
    }
  }

  /**
   * Tracks a page view.
   *
   * @param pageName The page to track.
   */
  public void trackPageView(final String pageName) {
    trackPageView(pageName, null);
  }
}
