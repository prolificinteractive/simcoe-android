package com.prolificinteractive.simcoe

class SimcoeException(error: String?) : RuntimeException(error ?: UNKNOWN_ERROR) {
  companion object {
    const val UNKNOWN_ERROR = "Unknown error"
  }
}