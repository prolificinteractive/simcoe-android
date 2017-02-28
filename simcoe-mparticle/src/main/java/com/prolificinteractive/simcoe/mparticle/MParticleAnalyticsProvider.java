package com.prolificinteractive.simcoe.mparticle;

import android.content.Context;
import com.mparticle.MParticle;
import com.prolificinteractive.simcoe.TrackingResult;
import com.prolificinteractive.simcoe.trackers.EventTracking;
import com.prolificinteractive.simcoe.trackers.PageViewTracking;
import java.util.Map;

public class MParticleAnalyticsProvider implements EventTracking, PageViewTracking {

  /**
   * Key to specify when providing a specific {@link MParticle.EventType} when tracking.
   */
  public static final String EVENT_TYPE = "SimcoeMPEventType";

  /**
   * Key to specify when providing a specific duration when tracking.
   */
  public static final String DURATION = "SimcoeMPDuration";

  @Override public String providerName() {
    return null;
  }

  @Override public void start(final Context context) {
    MParticle.start(context);
  }

  @Override public void stop() {
    // Ignore
  }

  @Override public TrackingResult trackEvent(
      final String eventName,
      final Map<String, Object> properties) {
    try {
      MParticle.getInstance().logEvent(MPEventUtils.toEvent(eventName, properties));
    } catch (final MPEventException e) {
      return new TrackingResult(e.getMessage());
    }
    return new TrackingResult(null);
  }

  @Override public TrackingResult trackPageView(
      final String pageName,
      final Map<String, Object> properties) {

    MParticle.getInstance().logScreen(pageName, MPEventUtils.toMParticleMap(properties));
    return new TrackingResult(null);
  }
}
