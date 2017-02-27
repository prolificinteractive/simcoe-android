package com.prolificinteractive.simcoe;

/**
 * The tracking result.
 */
public class TrackingResult {

  private final boolean error;
  private final String errorMessage;

  /**
   * Creates a tracking result instance.
   *
   * @param errorMessage The error message, or null.
   */
  public TrackingResult(final String errorMessage) {
    error = errorMessage != null;
    this.errorMessage = errorMessage;
  }

  /**
   * @return Error message.
   */
  public String getErrorMessage() {
    return errorMessage;
  }

  /**
   * Whether the tracking result contains an error message.
   */
  public boolean isError() {
    return error;
  }
}
