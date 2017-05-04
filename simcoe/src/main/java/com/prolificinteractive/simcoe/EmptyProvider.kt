package com.prolificinteractive.simcoe

import android.content.Context
import com.prolificinteractive.simcoe.trackers.ErrorLogging
import com.prolificinteractive.simcoe.trackers.EventTracking
import com.prolificinteractive.simcoe.trackers.LifetimeValueTracking
import com.prolificinteractive.simcoe.trackers.PageViewTracking
import com.prolificinteractive.simcoe.trackers.TimedEventTracking
import com.prolificinteractive.simcoe.trackers.UserAttributeTracking

/**
 * The empty provider.
 *
 * Used for logging analytics events when no other provider is specified.
 */
class EmptyProvider : ErrorLogging, EventTracking, LifetimeValueTracking, PageViewTracking, TimedEventTracking, UserAttributeTracking {
  companion object {
    private val EMPTY_PROVIDER_NAME = "Analytics"
  }

  override fun providerName(): String {
    return EMPTY_PROVIDER_NAME
  }

  override fun start(context: Context) {
    /* Nothing to do here. */
  }

  override fun stop() {
    /* Nothing to do here. */
  }

  override fun logError(error: String, properties: Properties?): TrackingResult {
    return Success()
  }

  override fun setUserAttribute(key: String, value: Any): TrackingResult {
    return Success()
  }

  override fun setUserAttributes(attributes: Properties?): TrackingResult {
    return Success()
  }

  override fun startTimedEvent(eventName: String, properties: Properties?): TrackingResult {
    return Success()
  }

  override fun stopTimedEvent(eventName: String, properties: Properties?): TrackingResult {
    return Success()
  }

  override fun trackEvent(eventName: String, properties: Properties?): TrackingResult {
    return Success()
  }

  override fun trackLifetimeValue(key: String, value: Any): TrackingResult {
    return Success()
  }

  override fun trackLifetimeValues(values: Properties?): TrackingResult {
    return Success()
  }

  override fun trackPageView(pageName: String, properties: Properties?): TrackingResult {
    return Success()
  }
}
