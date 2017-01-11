package com.prolificinteractive.simcoe.sample;

import android.content.Context;
import com.prolificinteractive.simcoe.Simcoe;
import com.prolificinteractive.simcoe.trackers.AnalyticsTracking;
import java.util.ArrayList;
import java.util.Set;
import org.json.JSONObject;
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
   * Starts the analytics engine, with the analytics providers.
   *
   * @param context The context.
   */
  public void start(final Context context) {
    Simcoe.run(context, providers != null ? new ArrayList<>(providers) : null);
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
  public void trackEvent(final String eventName, final JSONObject properties) {
    try {
      Simcoe.trackEvent(eventName, properties);
    } catch (final Exception e) {
      Timber.e(e, "error tracking event");
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

  /**
   * Tracks a page view.
   *
   * @param pageName The page to track.
   * @param properties The optional properties.
   */
  public void trackPageView(final String pageName, final JSONObject properties) {
    try {
      Simcoe.trackPageView(pageName, properties);
    } catch (final Exception e) {
      Timber.e(e, "error tracking page view");
    }
  }
}
