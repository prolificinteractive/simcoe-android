package com.prolificinteractive.simcoe;

import android.util.Pair;

/**
 * The tracking result.
 */
public class TrackingResult extends Pair<Boolean, String> {

  /**
   * Creates a tracking result instance.
   *
   * @param success The tracking result.
   * @param errorMessage The error message, or null.
   */
  public TrackingResult(final Boolean success, final String errorMessage) {
    super(success, errorMessage);
  }
}
