package com.prolificinteractive.simcoe.trackers

import com.prolificinteractive.simcoe.Properties
import com.prolificinteractive.simcoe.SimcoeProductConvertible
import com.prolificinteractive.simcoe.TrackingResult

/// Defines functionality for logging cart actions.
interface CartLogTracking : AnalyticsTracking {

    /// Logs the addition of a product to the cart.
    ///
    /// - Parameters:
    ///   - product: The SimcoeProductConvertible instance.
    ///   - eventProperties: The event properties.
    /// - Returns: A tracking result.
    fun <T : SimcoeProductConvertible> logAddToCart(product: T, eventProperties: Properties?): TrackingResult

    /// Logs the removal of a product from the cart.
    ///
    /// - Parameters:
    ///   - product: The SimcoeProductConvertible instance.
    ///   - eventProperties: The event properties.
    /// - Returns: A tracking result.
    fun <T : SimcoeProductConvertible> logRemoveFromCart(product: T, eventProperties: Properties?): TrackingResult
}