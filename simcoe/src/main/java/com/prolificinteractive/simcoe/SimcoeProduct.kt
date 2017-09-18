package com.prolificinteractive.simcoe

class SimcoeProduct(
        val productName: String,
        val productId: String,
        val quantity: Int,
        val price: Double?,
        val properties: Properties?
) {
    /// The default initializer.
    ///
    /// - Parameters:
    ///   - productName: The product name.
    ///   - productId: The product Id.
    ///   - quantity: The quantity.
    ///   - price: The price.
    ///   - properties: The properties.
}