package com.prolificinteractive.simcoe

import android.content.Context
import com.prolificinteractive.simcoe.trackers.AnalyticsTracking
import com.prolificinteractive.simcoe.trackers.ErrorLogging
import com.prolificinteractive.simcoe.trackers.EventTracking
import com.prolificinteractive.simcoe.trackers.LifetimeValueTracking
import com.prolificinteractive.simcoe.trackers.PageViewTracking
import com.prolificinteractive.simcoe.trackers.TimedEventTracking
import com.prolificinteractive.simcoe.trackers.UserAttributeTracking
import com.prolificinteractive.simcoe.trackers.findProviders
import java.util.ArrayList

/**
 * The root analytics
 *
 * @param tracker The analytics data tracker
 */
class Simcoe constructor(private val tracker: Tracker) {
  companion object {
    const private val ERROR_LOGGING_DESCRIPTION_FORMAT = "Error: %s => %s"
    const private val EVENT_TRACKING_DESCRIPTION_FORMAT = "Event: %s => %s"
    const private val LIFE_TIME_VALUE_DESCRIPTION_FORMAT = "Setting life time value with key: %s value: %s"
    const private val LIFETIME_VALUES_DESCRIPTION_FORMAT = "Lifetime Values: %s"
    const private val PAGE_VIEW_TRACKING_DESCRIPTION_FORMAT = "Page View: %s => %s"
    const private val TIMED_EVENT_TRACKING_START_DESCRIPTION_FORMAT = "Timed Event [Start]: %s => %s"
    const private val TIMED_EVENT_TRACKING_STOP_DESCRIPTION_FORMAT = "Timed Event [Stop]: %s => %s"
    const private val USER_ATTRIBUTE_DESCRIPTION_FORMAT = "Setting user attribute with key: %s value: %s"
    const private val USER_ATTRIBUTES_DESCRIPTION_FORMAT = "User Attributes: %s"
  }

  private val providers = ArrayList<AnalyticsTracking>()

  /**
   * Begins running using the passed in providers.

   * @param context The context.
   * @param providers The analytics tracker providers.
   */
  fun run(context: Context, providers: List<AnalyticsTracking>?) {
    // TODO Anything else?
    val analyticsProviders = ArrayList<AnalyticsTracking>()

    if (providers != null) {
      analyticsProviders.addAll(providers)
    }

    if (analyticsProviders.isEmpty()) {
      analyticsProviders.add(EmptyProvider())
    }

    setProviders(context, analyticsProviders)
  }

  /**
   * Writes the event.
   *
   * @param providers The list of providers.
   * @param description The event description.
   * @param action The function to call.
   * @throws SimcoeException The exception thrown when ErrorHandlingOption.STRICT is used.
   */
  private fun <T : AnalyticsTracking> write(
      providers: List<T>,
      description: String,
      action: (T) -> TrackingResult) {
    val writeEvents = providers.map { provider ->
      val result = action.invoke(provider)
      WriteEvent(provider, result)
    }

    tracker.track(Event(writeEvents, description))
  }

  /**
   * Stops analytics tracking on all the analytics providers.
   */
  fun stop() = providers.forEach(AnalyticsTracking::stop)

  /**
   * Sets the providers.
   *
   * @param context The context.
   * @param providers The analytics tracker providers.
   */
  private fun setProviders(context: Context, providers: List<AnalyticsTracking>) {
    this.providers.clear()
    this.providers.addAll(providers)
    providers.forEach { provider -> provider.start(context) }
  }

  /**
   * Logs an error.
   *
   * @param error The error to log.
   * @param properties The properties.
   * @throws SimcoeException The exception thrown when ErrorHandlingOption.STRICT is used.
   */
  @Throws(SimcoeException::class)
  @JvmOverloads
  fun logError(error: String,
               properties: Properties? = null,
               providers: List<AnalyticsTracking> = this.providers) {
    val activeProviders: List<ErrorLogging> = providers.findProviders()

    write(
        activeProviders,
        String.format(ERROR_LOGGING_DESCRIPTION_FORMAT, error, properties?.toString()),
        { errorLogging -> errorLogging.logError(error, properties) })
  }

  /**
   * Sets the user attribute.

   * @param key The attribute key to log.
   * @param value The attribute value to log.
   * @param providers The providers if custom.
   * @throws SimcoeException The exception thrown when ErrorHandlingOption.STRICT is used.
   */
  @Throws(SimcoeException::class)
  @JvmOverloads
  fun setUserAttribute(key: String,
                       value: Any,
                       providers: List<AnalyticsTracking> = this.providers) {
    val activeProviders: List<UserAttributeTracking> = providers.findProviders()

    write(
        activeProviders,
        String.format(USER_ATTRIBUTE_DESCRIPTION_FORMAT, key, value.toString()),
        { userAttributeTracking -> userAttributeTracking.setUserAttribute(key, value) }
    )
  }

  /**
   * Sets multiple user attributes.
   *
   * @param attributes The attributes to log.
   * @param providers The providers if custom.
   * @throws SimcoeException The exception thrown when ErrorHandlingOption.STRICT is used.
   */
  @Throws(SimcoeException::class)
  @JvmOverloads
  fun setUserAttributes(attributes: Properties? = null,
                        providers: List<AnalyticsTracking> = this.providers) {
    val activeProviders: List<UserAttributeTracking> = providers.findProviders()

    write(
        activeProviders,
        String.format(USER_ATTRIBUTES_DESCRIPTION_FORMAT, attributes?.toString()),
        { userAttributeTracking -> userAttributeTracking.setUserAttributes(attributes) }
    )
  }

  /**
   * Starts tracking a timed event.
   *
   * @param eventName The event name.
   * @param properties The properties
   * @param providers The providers if custom.
   * @throws SimcoeException The exception thrown when ErrorHandlingOption.STRICT is used.
   */
  @Throws(SimcoeException::class)
  @JvmOverloads
  fun startTimedEvent(eventName: String,
                      properties: Properties? = null,
                      providers: List<AnalyticsTracking> = this.providers) {
    val activeProviders: List<TimedEventTracking> = providers.findProviders()


    write(
        activeProviders,
        String.format(TIMED_EVENT_TRACKING_START_DESCRIPTION_FORMAT,
            eventName,
            properties?.toString()),
        { timedEventTracking -> timedEventTracking.startTimedEvent(eventName, properties) }
    )
  }

  /**
   * Stops tracking a timed event.
   *
   * @param eventName The event name.
   * @param properties The properties
   * @throws SimcoeException The exception thrown when ErrorHandlingOption.STRICT is used.
   */
  @Throws(SimcoeException::class)
  @JvmOverloads
  fun stopTimedEvent(eventName: String,
                     properties: Properties? = null,
                     providers: List<AnalyticsTracking> = this.providers) {
    val activeProviders: List<TimedEventTracking> = providers.findProviders()

    write(
        activeProviders,
        String.format(TIMED_EVENT_TRACKING_STOP_DESCRIPTION_FORMAT,
            eventName,
            properties?.toString()),
        { timedEventTracking -> timedEventTracking.stopTimedEvent(eventName, properties) }
    )
  }

  /**
   * Tracks an event with a given provider.
   *
   * @param eventName The event name.
   * @param properties The properties
   * @throws SimcoeException The exception thrown when ErrorHandlingOption.STRICT is used.
   */
  @Throws(SimcoeException::class)
  @JvmOverloads
  fun trackEvent(eventName: String,
                 properties: Properties? = null,
                 providers: List<AnalyticsTracking> = this.providers) {
    val activeProviders: List<EventTracking> = providers.findProviders()

    write(
        activeProviders,
        String.format(EVENT_TRACKING_DESCRIPTION_FORMAT, eventName, properties?.toString()),
        { eventTracking -> eventTracking.trackEvent(eventName, properties) }
    )
  }

  /**
   * Tracks the lifetime values for a given key.
   *
   * @param key The lifetime value's identifier.
   * @param value The lifetime value.
   * @throws SimcoeException The exception thrown when ErrorHandlingOption.STRICT is used.
   */
  @Throws(SimcoeException::class)
  @JvmOverloads
  fun trackLifetimeValue(key: String,
                         value: Any,
                         providers: List<AnalyticsTracking> = this.providers) {
    val activeProviders: List<LifetimeValueTracking> = providers.findProviders()

    write(
        activeProviders,
        String.format(LIFE_TIME_VALUE_DESCRIPTION_FORMAT, key, value.toString()),
        { lifetimeValueTracking -> lifetimeValueTracking.trackLifetimeValue(key, value) }
    )
  }

  /**
   * Tracks the lifetime values for a given JSON object.
   *
   * @param properties The values.
   * @throws SimcoeException The exception thrown when ErrorHandlingOption.STRICT is used.
   */
  @Throws(SimcoeException::class)
  @JvmOverloads
  fun trackLifetimeValues(properties: Properties? = null,
                          providers: List<AnalyticsTracking> = this.providers) {
    val activeProviders: List<LifetimeValueTracking> = providers.findProviders()

    write(
        activeProviders,
        String.format(LIFETIME_VALUES_DESCRIPTION_FORMAT, properties?.toString()),
        { lifetimeValueTracking -> lifetimeValueTracking.trackLifetimeValues(properties) }
    )
  }

  /**
   * Tracks a page view.
   *
   * @param pageName The page to track.
   * @param properties The properties.
   * @throws SimcoeException The exception thrown when ErrorHandlingOption.STRICT is used.
   */
  @JvmOverloads
  fun trackPageView(pageName: String,
                    properties: Properties? = null,
                    providers: List<AnalyticsTracking> = this.providers) {
    val activeProviders: List<PageViewTracking> = providers.findProviders()

    write(
        activeProviders,
        String.format(PAGE_VIEW_TRACKING_DESCRIPTION_FORMAT, pageName, properties?.toString()),
        { pageViewTracking -> pageViewTracking.trackPageView(pageName, properties) }
    )
  }
}


