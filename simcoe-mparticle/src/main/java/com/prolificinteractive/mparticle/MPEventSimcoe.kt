package com.prolificinteractive.mparticle

import com.mparticle.MPEvent
import com.mparticle.MParticle
import com.mparticle.MParticle.EventType
import com.prolificinteractive.simcoe.Properties
import java.util.Date

fun eventData(type: MParticle.EventType,
              name: String,
              category: String? = null,
              duration: Float? = null,
              startTime: Date? = null,
              endTime: Date? = null,
              customFlags: Map<String, MutableList<String>>? = null,
              info: Properties? = null): Properties {
    var properties: Properties = mutableMapOf(MPEventKeys.EVENT_TYPE.key to type, MPEventKeys.NAME.key to name)

    if (category != null) {
        properties.plus(MPEventKeys.CATEGORY.key to category)
    }

    if (duration != null) {
        properties.plus(MPEventKeys.DURATION.key to duration)
    }

    if (customFlags != null) {
        properties.plus(MPEventKeys.CUSTOM_FLAGS.key to customFlags)
    }

    if (info != null) {
        for ((key, value) in info) {
            properties.plus(key to value)
        }
    }

    return properties
}

@Suppress("UNCHECKED_CAST")
fun toEvent(data: Properties): MPEvent {
    if (data[MPEventKeys.EVENT_TYPE.key] == null || data[MPEventKeys.NAME.key] == null) {
        throw MPEventGenerationError("Failed to initialize the MPEvent. Check that you have a valid event name and type and try again. Names must not be an empty string.")
    }
    val eventType: MParticle.EventType = data[MPEventKeys.EVENT_TYPE.key] as EventType
    val eventName: String = data[MPEventKeys.NAME.key] as String
    var event: MPEvent = MPEvent.Builder(eventName, eventType)
            .category(data[MPEventKeys.CATEGORY.key] as String)
            .duration(data[MPEventKeys.DURATION.key] as Double)
            .build()
    event.customFlags.putAll(data[MPEventKeys.CUSTOM_FLAGS.key] as Map<String, MutableList<String>>)

    return event
}

class MPEventGenerationError(message: String?) : RuntimeException(message) {}

enum class MPEventKeys(val key : String) {
    EVENT_TYPE("SimcoeInternalMPEventType"),
    CATEGORY("SimcoeInternalMPCategory"),
    DURATION("SimcoeInternalMPDuration"),
    NAME("SimcoeInternalName"),
    CUSTOM_FLAGS("SimcoeInternalCustomFlags");
}