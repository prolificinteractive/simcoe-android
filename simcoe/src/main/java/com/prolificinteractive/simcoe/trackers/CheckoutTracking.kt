package com.prolificinteractive.simcoe.trackers

import com.prolificinteractive.simcoe.Properties
import com.prolificinteractive.simcoe.SimcoeProductConvertible
import com.prolificinteractive.simcoe.TrackingResult

/// Defines functionality for tracking checkout actions.
interface CheckoutTracking {

    /// Tracks a checkout event.
    ///
    /// - Parameters:
    ///   - products: The products.
    ///   - eventProperties: The event properties.
    /// - Returns: A tracking result.
    fun <T : SimcoeProductConvertible> trackCheckoutEvent(products: Array<T>, eventProperties: Properties?): TrackingResult
}