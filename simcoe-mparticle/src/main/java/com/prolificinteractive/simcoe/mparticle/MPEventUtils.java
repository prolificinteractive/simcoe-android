package com.prolificinteractive.simcoe.mparticle;

import com.mparticle.MPEvent;
import com.mparticle.MParticle;
import java.util.HashMap;
import java.util.Map;

import static com.prolificinteractive.simcoe.mparticle.MPEventErrorMessage.ATTRIBUTE_MISSING;
import static com.prolificinteractive.simcoe.mparticle.MPEventErrorMessage.EVENT_NAME_MISSING;
import static com.prolificinteractive.simcoe.mparticle.MParticleAnalyticsProvider.DURATION;
import static com.prolificinteractive.simcoe.mparticle.MParticleAnalyticsProvider.EVENT_TYPE;

class MPEventUtils {
  static MPEvent toEvent(final String eventName, final Map<String, Object> properties)
      throws MPEventException {
    if (eventName == null) {
      throw new MPEventException(EVENT_NAME_MISSING);
    }

    if (!properties.containsKey(EVENT_TYPE)) {
      throw new MPEventException(String.format(ATTRIBUTE_MISSING, EVENT_TYPE));
    }

    final MPEvent.Builder builder = new MPEvent.Builder(eventName);

    final MParticle.EventType trackEvent = (MParticle.EventType) properties.get(EVENT_TYPE);
    properties.remove(EVENT_TYPE);

    if (properties.containsKey(DURATION)) {
      final double duration = (double) properties.get(DURATION);
      properties.remove(DURATION);
      builder.duration(duration);
    }

    return builder
        .eventType(trackEvent)
        .info(toMParticleMap(properties))
        .build();
  }

  static Map<String, String> toMParticleMap(final Map<String, Object> properties) {
    final Map<String, String> attributes = new HashMap<>();
    for (final Map.Entry<String, Object> entry : properties.entrySet()) {
      attributes.put(entry.getKey(), String.valueOf(entry.getValue()));
    }
    return attributes;
  }
}
