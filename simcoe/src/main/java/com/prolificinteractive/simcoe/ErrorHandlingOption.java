package com.prolificinteractive.simcoe;

/**
 * Created by Michael Campbell on 1/4/17.
 */

/**
 * The various options for handling errors.
 */
public enum ErrorHandlingOption {
  /**
   * Suppresses errors; all errors are ignored.
   */
  SUPPRESS,

  /**
   * Default error handling; errors are logged to the output log.
   */
  DEFAULT,

  /**
   * String error handling; any errors encountered will trigger an exception.
   */
  STRICT
}
