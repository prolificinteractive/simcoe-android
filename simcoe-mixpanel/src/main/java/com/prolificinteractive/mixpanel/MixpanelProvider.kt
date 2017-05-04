package com.prolificinteractive.mixpanel

import android.content.Context
import com.mixpanel.android.mpmetrics.MixpanelAPI
import com.prolificinteractive.simcoe.Failure
import com.prolificinteractive.simcoe.Success
import com.prolificinteractive.simcoe.TrackingResult
import com.prolificinteractive.simcoe.trackers.EventTracking
import com.prolificinteractive.simcoe.trackers.LifetimeValueTracking
import com.prolificinteractive.simcoe.trackers.PageViewTracking
import com.prolificinteractive.simcoe.trackers.TimedEventTracking
import com.prolificinteractive.simcoe.trackers.UserAttributeTracking
import org.json.JSONException
import org.json.JSONObject

/**
 * Creates a new mixpanel analytics provider instance.
 */
class MixpanelProvider(private val token: String) : EventTracking, LifetimeValueTracking, PageViewTracking, UserAttributeTracking, TimedEventTracking {

    private var mixpanel: MixpanelAPI? = null

    companion object {
        private val MIXPANEL_PROVIDER_NAME = "Mixpanel"
    }

    override fun start(context: Context) {
        mixpanel = MixpanelAPI.getInstance(context, token)
    }

    override fun providerName(): String {
        return MIXPANEL_PROVIDER_NAME
    }

    override fun stop() {
        mixpanel!!.flush()
    }

    override fun trackEvent(
            eventName: String, properties: Map<String, Any>?): TrackingResult {
        mixpanel!!.trackMap(eventName, properties)
        return Success()
    }

    override fun setUserAttribute(key: String, value: Any): TrackingResult {
        mixpanel!!.people.set(key, value)
        return Success()
    }

    override fun setUserAttributes(attributes: Map<String, Any>?): TrackingResult {
        mixpanel!!.people.set(JSONObject(attributes))

        return Success()
    }

    override fun startTimedEvent(
            eventName: String, properties: Map<String, Any>?): TrackingResult {
        mixpanel!!.timeEvent(eventName)

        return Success()
    }

    override fun stopTimedEvent(
            eventName: String,
            properties: Map<String, Any>?): TrackingResult {
        // Intentionally calling track event here.
        // Mixpanel stops timed events when the same event name used to start the timer,
        // is called in a track event.
        return trackEvent(eventName, properties)
    }

    override fun trackLifetimeValue(key: String, value: Any): TrackingResult {
        val property = JSONObject()

        try {
            property.put(key, value)
        } catch (e: JSONException) {
            return Failure(e.localizedMessage)
        }

        mixpanel!!.registerSuperProperties(property)

        return Success()
    }

    override fun trackLifetimeValues(values: Map<String, Any>?): TrackingResult {
        mixpanel!!.registerSuperPropertiesMap(values)

        return Success()
    }

    override fun trackPageView(
            pageName: String, properties: Map<String, Any>?): TrackingResult {
        mixpanel!!.trackMap(pageName, properties)
        return Success()
    }
}
