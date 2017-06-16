package com.prolificinteractive.adobe

import android.content.Context
import com.adobe.mobile.Analytics
import com.adobe.mobile.AudienceManager
import com.prolificinteractive.simcoe.Properties
import com.prolificinteractive.simcoe.Success
import com.prolificinteractive.simcoe.TrackingResult
import com.prolificinteractive.simcoe.trackers.EventTracking
import com.prolificinteractive.simcoe.trackers.PageViewTracking
import com.prolificinteractive.simcoe.trackers.TimedEventTracking

/**
 * Creates a new adobe analytics provider instance.
 */

class AdobeProvider(private val token: String ="", private val appName: String) :
        EventTracking, PageViewTracking, TimedEventTracking {
    override fun startTimedEvent(eventName: String, properties: Properties?): TrackingResult {
        Analytics.trackTimedActionStart(eventName, properties)
        return Success()
    }

    override fun stopTimedEvent(eventName: String, properties: Properties?): TrackingResult {
        Analytics.trackTimedActionEnd(eventName, Analytics.TimedActionBlock { l1, l2, map ->
                map.putAll(properties as Map<out String, Any>)
                //needed to return it like this or else it tryed to return out of the whole fun
                return@TimedActionBlock true
            }
        )
        return Success()
    }

    companion object {
        private val ADOBE_PROVIDER_NAME = "Adobe"
        private val APP = "app"
    }

    override fun providerName(): String {
        return ADOBE_PROVIDER_NAME
    }

    override fun start(context: Context) {
        val map: Map<String, Any> = HashMap()
        map.plus(Pair(APP, appName))
        AudienceManager.signalWithData(map, null)
    }

    override fun trackPageView(pageName: String, properties: Properties?): TrackingResult {
        Analytics.trackState(pageName, properties)
        trackSingleValueWithAudienceManager(pageName, properties)
        return Success()
    }

    override fun trackEvent(eventName: String, properties: Properties?): TrackingResult {
        Analytics.trackAction(eventName, properties)
        AudienceManager.signalWithData(properties, null)
        return Success()
    }

    override fun stop() {
    }

    private fun trackSingleValueWithAudienceManager(value: String, properties: Properties?) {
        val map: Map<String, Any> = HashMap()
        map.plus(Pair(value, properties))
        AudienceManager.signalWithData(map, null)
    }

}