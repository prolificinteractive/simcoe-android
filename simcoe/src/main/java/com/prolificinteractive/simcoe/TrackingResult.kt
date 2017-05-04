package com.prolificinteractive.simcoe

/**
 * The tracking result.
 *
 * @param errorMessage Error message.
 */
open class TrackingResult(val errorMessage: String = "") {

  /**
   * Whether the tracking result contains an error message.
   */
  val isError: Boolean = errorMessage.isNotEmpty()

}

class Success : TrackingResult()

class Failure(errorMessage: String) : TrackingResult(errorMessage)