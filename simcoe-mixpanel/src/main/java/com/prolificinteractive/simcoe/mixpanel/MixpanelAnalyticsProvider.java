package com.prolificinteractive.simcoe.mixpanel;

import android.content.Context;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.prolificinteractive.simcoe.TrackingResult;
import com.prolificinteractive.simcoe.trackers.EventTracking;
import com.prolificinteractive.simcoe.trackers.LifetimeValueTracking;
import com.prolificinteractive.simcoe.trackers.PageViewTracking;
import com.prolificinteractive.simcoe.trackers.TimedEventTracking;
import com.prolificinteractive.simcoe.trackers.UserAttributeTracking;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The Mixpanel analytics provider.
 */
public class MixpanelAnalyticsProvider
    implements EventTracking,
    LifetimeValueTracking,
    PageViewTracking,
    UserAttributeTracking,
    TimedEventTracking {

  private static final String MIXPANEL_PROVIDER_NAME = "Mixpanel";

  private final String token;

  private MixpanelAPI mixpanel;

  /**
   * Creates a new mixpanel analytics provider instance.
   */
  public MixpanelAnalyticsProvider(final String token) {
    this.token = token;
  }

  @Override public String providerName() {
    return MIXPANEL_PROVIDER_NAME;
  }

  @Override public TrackingResult setUserAttribute(final String key, final Object value) {
    mixpanel.getPeople().set(key, value);

    return new TrackingResult(null);
  }

  @Override public TrackingResult setUserAttributes(final Map<String, Object> attributes) {
    mixpanel.getPeople().set(new JSONObject(attributes));

    return new TrackingResult(null);
  }

  @Override public void start(final Context context) {
    mixpanel = MixpanelAPI.getInstance(context, token);
  }

  @Override public TrackingResult startTimedEvent(
      final String eventName,
      final Map<String, Object> properties) {
    mixpanel.timeEvent(eventName);

    return new TrackingResult(null);
  }

  @Override public void stop() {
    mixpanel.flush();
  }

  @Override public TrackingResult stopTimedEvent(
      final String eventName,
      final Map<String, Object> properties) {
    // Intentionally calling track event here.
    // Mixpanel stops timed events when the same event name used to start the timer,
    // is called in a track event.
    return trackEvent(eventName, properties);
  }

  @Override public TrackingResult trackEvent(
      final String eventName,
      final Map<String, Object> properties) {
    mixpanel.track(eventName, new JSONObject(properties));

    return new TrackingResult(null);
  }

  @Override public TrackingResult trackLifetimeValue(final String key, final Object value) {
    final JSONObject property = new JSONObject();

    try {
      property.put(key, value);
    } catch (final JSONException e) {
      return new TrackingResult(e.getLocalizedMessage());
    }

    mixpanel.registerSuperProperties(property);

    return new TrackingResult(null);
  }

  @Override public TrackingResult trackLifetimeValues(final Map<String, Object> values) {
    mixpanel.registerSuperProperties(new JSONObject(values));

    return new TrackingResult(null);
  }

  @Override public TrackingResult trackPageView(
      final String pageName,
      final Map<String, Object> properties) {
    mixpanel.track(pageName, new JSONObject(properties));

    return new TrackingResult(null);
  }
}
