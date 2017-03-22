package com.prolificinteractive.simcoe.trackers

import com.prolificinteractive.simcoe.Properties
import com.prolificinteractive.simcoe.TrackingResult

/**
 * The user attribute tracking interface, used with analytics.
 */
interface UserAttributeTracking : AnalyticsTracking {

    /**
     * Sets the user attribute.

     * @param key The attribute key to log.
     * *
     * @param value The attribute value to log.
     * *
     * @return A tracking result.
     */
    fun setUserAttribute(key: String, value: Any): TrackingResult

    /**
     * Sets multiple user attributes.

     * @param attributes The attributes to log.
     * *
     * @return A tracking result.
     */
    fun setUserAttributes(attributes: Properties?): TrackingResult
}
