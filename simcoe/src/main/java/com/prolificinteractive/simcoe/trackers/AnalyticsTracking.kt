package com.prolificinteractive.simcoe.trackers

import android.content.Context

/**
 * The analytics provider interface.
 */
interface AnalyticsTracking {

    /**
     * The name of the tracker. This is used for debugging purposes only, but should be reflective
     * of the tool you are using to track analytics.

     * @return The name of the provider.
     */
    fun providerName(): String

    /**
     * Starts the analytics provider.

     * This is called on all providers passed to `Simcoe.run`. Your analytics tracker should start
     * tracking lifecycle data or setup anything else that needs to run for the duration of the
     * analytics lifecycle.

     * @param context The context.
     */
    fun start(context: Context) {

    }

    /**
     * Stops the analytics provider.

     * This should be called before the application terminates or enters the background. Some
     * providers suggest that a stop event should be called to make sure events are fired before the
     * app dismisses, else they may fire at the next start of the app.
     */
    fun stop()
}
