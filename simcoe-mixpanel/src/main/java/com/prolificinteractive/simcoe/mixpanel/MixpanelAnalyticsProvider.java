package com.prolificinteractive.simcoe.mixpanel;

import android.content.Context;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.prolificinteractive.simcoe.TrackingResult;
import com.prolificinteractive.simcoe.trackers.EventTracking;
import com.prolificinteractive.simcoe.trackers.PageViewTracking;
import org.json.JSONObject;

/**
 * The Mixpanel analytics provider.
 */
public class MixpanelAnalyticsProvider implements EventTracking, PageViewTracking {

  private final String token;
  private final String MIXPANEL_PROVIDER_NAME = "Mixpanel";
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

  @Override public void start(final Context context) {
    mixpanel = MixpanelAPI.getInstance(context, token);
  }

  @Override public TrackingResult trackEvent(
      final String eventName,
      final JSONObject properties) {
    mixpanel.track(eventName, properties);

    return new TrackingResult(true, null);
  }

  @Override public TrackingResult trackPageView(
      final String pageName,
      final JSONObject properties) {
    mixpanel.track(pageName, properties);

    return new TrackingResult(true, null);
  }
}
