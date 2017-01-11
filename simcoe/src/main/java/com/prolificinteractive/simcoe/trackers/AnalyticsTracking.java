package com.prolificinteractive.simcoe.trackers;

/**
 * Created by Michael Campbell on 12/23/16.
 */

import android.content.Context;

/**
 * The analytics provider interface.
 */
public interface AnalyticsTracking {

  /**
   * The name of the tracker. This is used for debugging purposes only, but should be reflective
   * of the tool you are using to track analytics.
   *
   * @return The name of the provider.
   */
  String providerName();

  /**
   * Starts the analytics provider.
   *
   * This is called on all providers passed to `Simcoe.run`. Your analytics tracker should start
   * tracking lifecycle data or setup anything else that needs to run for the duration of the
   * analytics lifecycle.
   *
   * @param context The context.
   */
  void start(Context context);
}
