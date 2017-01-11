package com.prolificinteractive.simcoe;

import android.util.Log;

/**
 * The console output.
 */
public class ConsoleOutput implements Output {

  private final String SIMCOE_LOG_TAG = "Simcoe";

  @Override public void print(final String message) {
    Log.v(SIMCOE_LOG_TAG, message);
  }
}
