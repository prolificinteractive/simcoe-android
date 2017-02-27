package com.prolificinteractive.simcoe;

/**
 * The various options for how to output records to the standard output.
 */
public enum OutputOptions {

  /**
   * No output; disables records displaying in the console.
   */
  NONE,

  /**
   * Only outputs one item per event, no matter how many providers were recorded.
   */
  SIMPLE,

  /**
   * Outputs one line for each provider per event, specifying which provider was recorded.
   * This is the best option if you are using many providers and want to verify data is being
   * output to each one.
   */
  VERBOSE
}
