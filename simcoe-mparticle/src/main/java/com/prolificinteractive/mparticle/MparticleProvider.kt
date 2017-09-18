package com.prolificinteractive.mparticle

import android.content.Context
import com.mparticle.MPEvent
import com.mparticle.MParticle
import com.mparticle.commerce.Product
import com.prolificinteractive.simcoe.Failure
import com.prolificinteractive.simcoe.Properties
import com.prolificinteractive.simcoe.SimcoeProductConvertible
import com.prolificinteractive.simcoe.Success
import com.prolificinteractive.simcoe.TrackingResult
import com.prolificinteractive.simcoe.trackers.CartLogTracking
import com.prolificinteractive.simcoe.trackers.CheckoutTracking
import com.prolificinteractive.simcoe.trackers.EventTracking
import com.prolificinteractive.simcoe.trackers.LifetimeValueTracking
import com.prolificinteractive.simcoe.trackers.PageViewTracking
import com.prolificinteractive.simcoe.trackers.TimedEventTracking
import java.math.BigDecimal
import java.util.Arrays

/**
 * Creates a new mParticle analytics provider instance.
 */
class MparticleProvider(private val token: String, private val secret: String) :
        EventTracking,
        PageViewTracking,
        LifetimeValueTracking,
        TimedEventTracking,
        CartLogTracking,
        CheckoutTracking {

    companion object {
        private val MPARTICLE_PROVIDER_NAME = "MParticle"
    }

    /** Initializes and starts the SDK with the input key and secret.
     *
     * - Parameters:
     *   - key: The key.
     *   - secret: The secret.
     **/
    override fun start(context: Context) {
        MParticle.start(context, token, secret)
    }

    override fun stop() {}

    override fun providerName(): String {
        return MPARTICLE_PROVIDER_NAME
    }

    /** Tracks a checkout event.
     *
     * - Parameters:
     *   - products: The products.
     *   - eventProperties: The event properties.
     * - Returns: A tracking result.
     **/
    override fun <T : SimcoeProductConvertible> trackCheckoutEvent(products: Array<T>, eventProperties: Properties?): TrackingResult {
        val mpProducts = products.map { product -> mpProduct(product) }.toTypedArray()

        val event = getCommerceEvent(Product.CHECKOUT, mpProducts, eventProperties)

        MParticle.getInstance().logEvent(event)
        return Success()
    }

    /** Logs the addition of a product to the cart.
     *
     * - Parameters:
     *   - product: The SimcoeProductConvertible instance.
     *   - eventProperties: The event properties.
     * - Returns: A tracking result.
     **/
    override fun <T : SimcoeProductConvertible> logAddToCart(product: T, eventProperties: Properties?): TrackingResult {
        val mpProducts = Arrays.asList(mpProduct(product)).toTypedArray()
        val event = getCommerceEvent(Product.ADD_TO_CART, mpProducts, eventProperties)
        MParticle.getInstance().logEvent(event)
        return Success()
    }

    /** Logs the removal of a product from the cart.
     *
     * - Parameters:
     *   - product: The SimcoeProductConvertible instance.
     *   - eventProperties: The event properties.
     * - Returns: A tracking result.
     **/
    override fun <T : SimcoeProductConvertible> logRemoveFromCart(product: T, eventProperties: Properties?): TrackingResult {
        val mpProducts = Arrays.asList(mpProduct(product)).toTypedArray()
        val event = getCommerceEvent(Product.REMOVE_FROM_CART, mpProducts, eventProperties)
        MParticle.getInstance().logEvent(event)
        return Success()
    }

    /** Tracks the lifetime value.
     *
     * - Parameters:
     *   - key: The lifetime value's identifier.
     *   - value: The lifetime value.
     *   - properties: The optional additional properties.
     * - Returns: A tracking result.
     **/
    override fun trackLifetimeValue(key: String, value: Any, properties: Properties?): TrackingResult {
        val valDecimal = value as? BigDecimal ?: return Failure("Value must map to a Double")
        val newProps = properties?.mapValues { item -> item.toString() }
        MParticle.getInstance().logLtvIncrease(valDecimal, key, newProps)

        return Success()
    }


    /** Track the lifetime values.
     *
     * - Parameter:
     *   - attributes: The lifetime attribute values.
     *   - properties: The optional additional properties.
     * - Returns: A tracking result.
     **/
    override fun trackLifetimeValues(attributes: Properties, values: Properties?): TrackingResult {
        attributes.map { property -> trackLifetimeValue(property.key, property.value, values) }

        return Success()
    }

    /** Starts the timed event.
     *
     * - Parameters:
     *   - event: The event name.
     *   - properties: The event properties.
     * - Returns: A tracking result.
     **/
    override fun startTimedEvent(eventName: String, properties: Properties?): TrackingResult {
        if (properties == null) {
            return Failure("Cannot track a timed event without valid properties.")
        }

        properties.plus(MPEventKeys.NAME.key to eventName)

        val event: MPEvent

        try {
            event = toEvent(properties)
        } catch (error: MPEventGenerationError) {
            return Failure(error.message ?: "Unable to generate event")
        } catch (error: Error) {
            return Failure("Unknown error")
        }

        MParticle.getInstance().logEvent(event)
        return Success()
    }

    /** Stops the timed event.
     *
     * - Parameters:
     *   - event: The event name.
     *   - properties: The event properties.
     * - Returns: A tracking result.
     **/
    override fun stopTimedEvent(eventName: String, properties: Properties?): TrackingResult {
        return startTimedEvent(eventName, properties)
    }

    /** Tracks an mParticle event.
     *
     * Internally, this generates an MPEvent object based on the properties passed in . The event string
     * passed as the first parameter is delineated as the .name of the MPEvent. As a caller, you are
     * required to pass in non-nil properties where one of the properties is the MPEventType. Failure
     * to do so will cause this function to fail.
     *
     * It is recommended that you use the `Simcoe.eventData()` function in order to generate the properties
     * dictionary properly.
     *
     * - Parameters:
     *   - event: The event name to log.
     *   - properties: The properties of the event.
     * - Returns: A tracking result.
     **/
    override fun trackEvent(eventName: String, properties: Properties?): TrackingResult {
        var mProperties: Properties = properties ?: return Failure("Cannot track an event without valid properties.")
        mProperties.plus(properties[MPEventKeys.NAME.key] to eventName)
        val event: MPEvent = toEvent(mProperties)
        MParticle.getInstance().logEvent(event)
        return Success()
    }

    /** Tracks the page view.
     *
     * - Parameters:
     *   - pageView: The page view to track.
     *   - properties: The optional additional properties.
     * - Returns: A tracking result.
     **/
    override fun trackPageView(pageName: String, properties: Properties?): TrackingResult {
        //TODO 9/19/17 Figure out what to do with the properties object here
        MParticle.getInstance().logScreen(pageName)
        return Success()
    }
}
