package com.prolificinteractive.simcoe.trackers;

import com.prolificinteractive.simcoe.TrackingResult;
import org.json.JSONObject;

/**
 * The user attribute tracking interface, used with analytics.
 */
public interface UserAttributeTracking extends AnalyticsTracking {

  /**
   * Sets the user attribute.
   *
   * @param key The attribute key to log.
   * @param value The attribute value to log.
   * @return A tracking result.
   */
  TrackingResult setUserAttribute(String key, Object value);

  /**
   * Sets multiple user attributes.
   *
   * @param attributes The attributes to log.
   * @return A tracking result.
   */
  TrackingResult setUserAttributes(JSONObject attributes);
}
