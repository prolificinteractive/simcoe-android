package com.prolificinteractive.simcoe

/**
 * The various options for handling errors.
 */
enum class ErrorHandlingOption {
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
