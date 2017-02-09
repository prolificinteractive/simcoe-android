package com.prolificinteractive.simcoe;

import android.content.Context;
import com.prolificinteractive.simcoe.trackers.AnalyticsTracking;
import com.prolificinteractive.simcoe.trackers.ErrorLogging;
import com.prolificinteractive.simcoe.trackers.EventTracking;
import com.prolificinteractive.simcoe.trackers.LifetimeValueTracking;
import com.prolificinteractive.simcoe.trackers.PageViewTracking;
import com.prolificinteractive.simcoe.trackers.TimedEventTracking;
import com.prolificinteractive.simcoe.trackers.UserAttributeTracking;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 * The root analytics engine.
 */
public class Simcoe {

  public static final Simcoe engine = new Simcoe(new Tracker(null));

  private static final String EMPTY_STRING = "";
  private static final String ERROR_LOGGING_DESCRIPTION_FORMAT = "Error: %s %s";
  private static final String EVENT_TRACKING_DESCRIPTION_FORMAT = "Event: %s %s";
  private static final String LIFE_TIME_VALUE_DESCRIPTION_FORMAT =
      "Setting life time value with key: %s value: %s";
  private static final String LIFETIME_VALUES_DESCRIPTION_FORMAT = "Lifetime Values: %s";
  private static final String PAGE_VIEW_TRACKING_DESCRIPTION_FORMAT = "Page View: %s %s";
  private static final String PROPERTY_STRING_FORMAT = "=> %s";
  private static final String TIMED_EVENT_TRACKING_START_DESCRIPTION_FORMAT =
      "Timed Event [Start]: %s %s";
  private static final String TIMED_EVENT_TRACKING_STOP_DESCRIPTION_FORMAT =
      "Timed Event [Stop]: %s %s";
  private static final String USER_ATTRIBUTE_DESCRIPTION_FORMAT =
      "Setting user attribute with key: %s value: %s";
  private static final String USER_ATTRIBUTES_DESCRIPTION_FORMAT = "User Attributes: %s";

  private final List<AnalyticsTracking> providers = new ArrayList<>();
  /**
   * The analytics data tracker.
   */
  private final Tracker tracker;

  /**
   * Creates a Simcoe instance.
   *
   * @param tracker The tracker.
   */
  public Simcoe(final Tracker tracker) {
    this.tracker = tracker;
  }

  /**
   * Begins running using the passed in providers.
   *
   * @param context The context.
   * @param providers The analytics tracker providers.
   */
  public static void run(final Context context, final List<AnalyticsTracking> providers) {
    final List<AnalyticsTracking> analyticsProviders = new ArrayList<>();

    if (providers != null) {
      analyticsProviders.addAll(providers);
    }

    if (analyticsProviders.isEmpty()) {
      analyticsProviders.add(new EmptyProvider());
    }

    engine.setProviders(context, analyticsProviders);
  }

  /**
   * Stops analytics tracking on all the analytics providers.
   */
  public static void stop() {
    for (final AnalyticsTracking provider : engine.providers) {
      provider.stop();
    }
  }

  // region ErrorLogging

  /**
   * Logs an error.
   *
   * @param error The error to log.
   * @param properties The properties.
   * @throws Exception The exception thrown when ErrorHandlingOption.STRICT is used.
   */
  public static void logError(final String error, final JSONObject properties) throws Exception {
    final List<ErrorLogging> providers = engine.findProviders(ErrorLogging.class);

    final String propertiesString = propertiesString(properties);

    engine.write(
        providers,
        String.format(ERROR_LOGGING_DESCRIPTION_FORMAT, error, propertiesString),
        errorLogging -> errorLogging.logError(error, properties)
    );
  }

  // endregion ErrorLogging

  // region EventTracking

  /**
   * Tracks an event with a given provider.
   *
   * @param eventName The event name.
   * @param properties The properties
   * @throws Exception The exception thrown when ErrorHandlingOption.STRICT is used.
   */
  public static void trackEvent(final String eventName, final JSONObject properties)
      throws Exception {
    final List<EventTracking> providers = engine.findProviders(EventTracking.class);

    final String propertiesString = propertiesString(properties);

    engine.write(
        providers,
        String.format(EVENT_TRACKING_DESCRIPTION_FORMAT, eventName, propertiesString),
        eventTracking -> eventTracking.trackEvent(eventName, properties)
    );
  }

  // endregion EventTracking

  // region LifetimeValueTracking

  /**
   * Tracks the lifetime values for a given key.
   *
   * @param key The lifetime value's identifier.
   * @param value The lifetime value.
   * @throws Exception The exception thrown when ErrorHandlingOption.STRICT is used.
   */
  public static void trackLifetimeValue(final String key, final Object value) throws Exception {
    final List<LifetimeValueTracking> providers = engine.findProviders(LifetimeValueTracking.class);

    engine.write(
        providers,
        String.format(LIFE_TIME_VALUE_DESCRIPTION_FORMAT, key, value.toString()),
        lifetimeValueTracking -> lifetimeValueTracking.trackLifetimeValue(key, value)
    );
  }

  /**
   * Tracks the lifetime values for a given JSON object.
   *
   * @param values The values.
   * @throws Exception The exception thrown when ErrorHandlingOption.STRICT is used.
   */
  public static void trackLifetimeValues(final JSONObject values) throws Exception {
    final List<LifetimeValueTracking> providers = engine.findProviders(LifetimeValueTracking.class);

    final String propertiesString = propertiesString(values);

    engine.write(
        providers,
        String.format(LIFETIME_VALUES_DESCRIPTION_FORMAT, propertiesString),
        lifetimeValueTracking -> lifetimeValueTracking.trackLifetimeValues(values)
    );
  }

  // endregion LifetimeValueTracking

  // region PageViewTracking

  /**
   * Tracks a page view.
   *
   * @param pageName The page to track.
   * @param properties The properties.
   * @throws Exception The exception thrown when ErrorHandlingOption.STRICT is used.
   */
  public static void trackPageView(final String pageName, final JSONObject properties)
      throws Exception {
    final List<PageViewTracking> providers = engine.findProviders(PageViewTracking.class);

    final String propertiesString = propertiesString(properties);

    engine.write(
        providers,
        String.format(PAGE_VIEW_TRACKING_DESCRIPTION_FORMAT, pageName, propertiesString),
        pageViewTracking -> pageViewTracking.trackPageView(pageName, properties)
    );
  }

  // endregion PageViewTracking

  // region TimedEventTracking

  /**
   * Starts tracking a timed event.
   *
   * @param eventName The event name.
   * @param properties The properties
   * @throws Exception The exception thrown when ErrorHandlingOption.STRICT is used.
   */
  public static void startTimedEvent(final String eventName, final JSONObject properties)
      throws Exception {
    final List<TimedEventTracking> providers = engine.findProviders(TimedEventTracking.class);

    final String propertiesString = propertiesString(properties);

    engine.write(
        providers,
        String.format(TIMED_EVENT_TRACKING_START_DESCRIPTION_FORMAT, eventName, propertiesString),
        timedEventTracking -> timedEventTracking.startTimedEvent(eventName, properties)
    );
  }

  /**
   * Stops tracking a timed event.
   *
   * @param eventName The event name.
   * @param properties The properties
   * @throws Exception The exception thrown when ErrorHandlingOption.STRICT is used.
   */

  public static void stopTimedEvent(final String eventName, final JSONObject properties)
      throws Exception {
    final List<TimedEventTracking> providers = engine.findProviders(TimedEventTracking.class);

    final String propertiesString = propertiesString(properties);

    engine.write(
        providers,
        String.format(TIMED_EVENT_TRACKING_STOP_DESCRIPTION_FORMAT, eventName, propertiesString),
        timedEventTracking -> timedEventTracking.stopTimedEvent(eventName, properties)
    );
  }

  // endregion TimedEventTracking

  // region UserAttributeTracking

  /**
   * Sets the user attribute.
   *
   * @param key The attribute key to log.
   * @param value The attribute value to log.
   * @throws Exception The exception thrown when ErrorHandlingOption.STRICT is used.
   */
  public static void setUserAttribute(final String key, final Object value) throws Exception {
    final List<UserAttributeTracking> providers = engine.findProviders(UserAttributeTracking.class);

    engine.write(
        providers,
        String.format(USER_ATTRIBUTE_DESCRIPTION_FORMAT, key, value.toString()),
        userAttributeTracking -> userAttributeTracking.setUserAttribute(key, value)
    );
  }

  /**
   * Sets multiple user attributes.
   *
   * @param attributes The attributes to log.
   * @throws Exception The exception thrown when ErrorHandlingOption.STRICT is used.
   */
  public static void setUserAttributes(final JSONObject attributes) throws Exception {
    final List<UserAttributeTracking> providers = engine.findProviders(UserAttributeTracking.class);

    final String propertiesString = propertiesString(attributes);

    engine.write(
        providers,
        String.format(USER_ATTRIBUTES_DESCRIPTION_FORMAT, propertiesString),
        userAttributeTracking -> userAttributeTracking.setUserAttributes(attributes)
    );
  }

  // endregion UserAttributeTracking

  private static String propertiesString(final JSONObject properties) {
    String propertiesString = EMPTY_STRING;

    if (properties != null) {
      propertiesString = String.format(PROPERTY_STRING_FORMAT, properties.toString());
    }

    return propertiesString;
  }

  /**
   * Writes the event.
   *
   * @param providers The list of providers.
   * @param description The event description.
   * @param action The function to call.
   * @param <T> An AnalyticsTracking extender.
   * @throws Exception The exception thrown when ErrorHandlingOption.STRICT is used.
   */
  public <T extends AnalyticsTracking> void write(
      final List<T> providers,
      final String description,
      final Func1<T, TrackingResult> action) throws Exception {
    final List<WriteEvent> writeEvents = new ArrayList<>();

    for (final T provider : providers) {
      final TrackingResult result = action.call(provider);
      writeEvents.add(new WriteEvent(provider, result));
    }

    final Event event = new Event(writeEvents, description, null);
    tracker.track(event);
  }

  private <T extends AnalyticsTracking> List<T> findProviders(final Class<T> clazz) {
    final List<T> typedProviders = new ArrayList<>();
    for (final AnalyticsTracking provider : providers) {
      if (clazz.isInstance(provider)) {
        typedProviders.add((T) provider);
      }
    }

    return typedProviders;
  }

  /**
   * Sets the providers.
   *
   * @param context The context.
   * @param providers The analytics tracker providers.
   */
  private void setProviders(final Context context, final List<AnalyticsTracking> providers) {
    this.providers.clear();
    this.providers.addAll(providers);
    for (final AnalyticsTracking provider : providers) {
      provider.start(context);
    }
  }
}
