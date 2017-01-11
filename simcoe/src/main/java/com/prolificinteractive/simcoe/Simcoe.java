package com.prolificinteractive.simcoe;

import android.content.Context;
import com.prolificinteractive.simcoe.trackers.AnalyticsTracking;
import com.prolificinteractive.simcoe.trackers.ErrorLogging;
import com.prolificinteractive.simcoe.trackers.EventTracking;
import com.prolificinteractive.simcoe.trackers.PageViewTracking;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 * The root analytics engine.
 */
public class Simcoe {

  public static final String EMPTY_STRING = "";
  public static final String ERROR_LOGGING_DESCRIPTION_FORMAT = "Error: %s %s";
  public static final String EVENT_TRACKING_DESCRIPTION_FORMAT = "Event: %s %s";
  public static final String PAGE_VIEW_TRACKING_DESCRIPTION_FORMAT = "Page View: %s %s";
  public static final String PROPERTY_STRING_FORMAT = "=> %s";

  public static final Simcoe engine = new Simcoe(new Tracker(null));
  private final List<AnalyticsTracking> providers = new ArrayList<>();
  /**
   * The analytics data tracker.
   */
  private Tracker tracker;

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
   * @param myProviders The analytics tracker providers.
   */
  public static void run(final Context context, final List<AnalyticsTracking> myProviders) {
    if (myProviders == null || myProviders.isEmpty()) {
      final List<AnalyticsTracking> providers = new ArrayList<>();
      providers.add(new EmptyProvider());
      engine.setProviders(context, providers);
    } else {
      engine.setProviders(context, myProviders);
    }
  }

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

    String propertiesString = EMPTY_STRING;
    if (properties != null) {
      propertiesString = String.format(PROPERTY_STRING_FORMAT, properties.toString());
    }

    engine.write(
        providers,
        String.format(EVENT_TRACKING_DESCRIPTION_FORMAT, eventName, propertiesString),
        new Func1<EventTracking, TrackingResult>() {
          @Override public TrackingResult call(final EventTracking eventTracking) {
            return eventTracking.trackEvent(eventName, properties);
          }
        }
    );
  }

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

    String propertiesString = EMPTY_STRING;
    if (properties != null) {
      propertiesString = String.format(PROPERTY_STRING_FORMAT, properties.toString());
    }

    engine.write(
        providers,
        String.format(PAGE_VIEW_TRACKING_DESCRIPTION_FORMAT, pageName, propertiesString),
        new Func1<PageViewTracking, TrackingResult>() {
          @Override public TrackingResult call(final PageViewTracking pageViewTracking) {
            return pageViewTracking.trackPageView(pageName, properties);
          }
        }
    );
  }

  /**
   * Logs an error.
   *
   * @param error The error to log.
   * @param properties The properties.
   * @throws Exception The exception thrown when ErrorHandlingOption.STRICT is used.
   */
  public static void logError(final String error, final JSONObject properties) throws Exception {
    final List<ErrorLogging> providers = engine.findProviders(ErrorLogging.class);

    String propertiesString = EMPTY_STRING;
    if (properties != null) {
      propertiesString = String.format(PROPERTY_STRING_FORMAT, properties.toString());
    }

    engine.write(
        providers,
        String.format(ERROR_LOGGING_DESCRIPTION_FORMAT, error, propertiesString),
        new Func1<ErrorLogging, TrackingResult>() {
          @Override public TrackingResult call(final ErrorLogging errorLogging) {
            return errorLogging.logError(error, properties);
          }
        }
    );
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
