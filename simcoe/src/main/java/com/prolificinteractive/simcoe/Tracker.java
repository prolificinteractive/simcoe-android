package com.prolificinteractive.simcoe;

import com.prolificinteractive.simcoe.trackers.AnalyticsTracking;
import java.util.ArrayList;
import java.util.List;

/**
 * A recorder for SimcoeEvents.
 */
public final class Tracker {

  private static final String ANALYTICS_PROVIDER_NAME = "Analytics";
  private static final String WRITE_OUT_FORMAT = "[%s] %s";
  private static final String ANALYTICS_ERROR = "** ANALYTICS ERROR **";

  private final List<Event> events = new ArrayList<>();
  private final List<Output> outputSources;

  /**
   * The output options.
   */
  OutputOptions outputOption = OutputOptions.VERBOSE;

  /**
   * The error options.
   */
  ErrorHandlingOption errorOption = ErrorHandlingOption.DEFAULT;

  /**
   * Creates a new tracker instance.
   *
   * @param outputSources The source to use for general output. If no output sources are provided,
   * the default is set as the standard output console.
   */
  public Tracker(final List<Output> outputSources) {
    if (outputSources != null) {
      this.outputSources = outputSources;
    } else {
      this.outputSources = new ArrayList<>();
      this.outputSources.add(new ConsoleOutput());
    }
  }

  private void handleError(final String message) throws
      Exception {
    switch (errorOption) {
      case DEFAULT:
        writeOut(ANALYTICS_ERROR, message);
        break;
      case STRICT:
        throw new Exception(message);
      case SUPPRESS:
        break;
    }
  }

  private void parseEvent(final Event event) throws Exception {
    final List<AnalyticsTracking> successfulProviders = new ArrayList<>();

    for (final WriteEvent writeEvent : event.writeEvents) {
      if (writeEvent != null && writeEvent.trackingResult.first) {
        successfulProviders.add(writeEvent.provider);
      } else {
        if (writeEvent != null) {
          handleError(writeEvent.trackingResult.second);
        }

        return;
      }
    }

    write(event.description, successfulProviders);
  }

  /**
   * Records the event.
   *
   * @param event The event to record.
   * @throws Exception The exception thrown when ErrorHandlingOption.STRICT is used.
   */
  void track(final Event event) throws Exception {
    events.add(event);
    parseEvent(event);
  }

  private void write(final String description, final List<AnalyticsTracking> providers) {
    switch (outputOption) {
      case NONE:
        return;
      case VERBOSE:
        for (final AnalyticsTracking provider : providers) {
          writeOut(provider.providerName(), description);
        }
        break;
      case SIMPLE:
        writeOut(ANALYTICS_PROVIDER_NAME, description);
        break;
    }
  }

  private void writeOut(final String name, final String message) {
    for (final Output source : outputSources) {
      source.print(String.format(WRITE_OUT_FORMAT, name, message));
    }
  }
}
