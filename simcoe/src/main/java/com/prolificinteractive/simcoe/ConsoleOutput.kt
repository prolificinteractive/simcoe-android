package com.prolificinteractive.simcoe

import android.util.Log

/**
 * The console output.
 */
internal class ConsoleOutput : Output {
  private val SIMCOE_LOG_TAG = "Simcoe"

  override fun print(message: String) {
    Log.v(SIMCOE_LOG_TAG, message)
  }
}
