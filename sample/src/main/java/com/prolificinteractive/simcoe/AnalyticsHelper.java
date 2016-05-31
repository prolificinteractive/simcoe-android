package com.prolificinteractive.simcoe;

import android.content.Context;
import android.os.Bundle;
import com.prolificinteractive.simcoe.api.Analytics;
import com.prolificinteractive.simcoe.api.Ignore;

@Analytics
public class AnalyticsHelper {
  // Use constant for your tags. List your tags in one place.
  public static final String SCREEN = "screen";
  public static final String EVENT = "event";

  // TAGs
  public static final String TAG_NAME = "name";
  public static final String TAG_COUNT = "count";

  // You can annotate using ignore if you don't want a sub-method to be created
  @Ignore
  public void initYourEventHandler(final Context context) {
    // Init your analytics tool here, like:
    // MyAnalyticsTool.init(context);
  }

  // Create a method per event tracking. The method should handle all the tracking of the event, using the
  // different parameters of the method.

  // Each method should be public static void.

  public void trackSomething(final String name) {
    // MyAnalyticsTool.track(EVENT, name);
  }

  public void trackAnotherThing(final String name, final int count) {
    final Bundle bundle = new Bundle();

    bundle.putString(TAG_NAME, name);
    bundle.putString(TAG_COUNT, transformCount(count));

    // MyAnalyticsTool.track(EVENT, bundle);
  }

  // You can use a track screen method for all your screens
  public void trackScreen(final String name) {
    // MyAnalyticsTool.track(SCREEN, name);
  }

  // Add handy methods to convert your data for your analytics tool.
  // Annotate them with ignore so they don't get logged
  // List your private method at the end of your class

  @Ignore
  private String transformCount(final int count) {
    return Integer.toString(count + 1);
  }
}
