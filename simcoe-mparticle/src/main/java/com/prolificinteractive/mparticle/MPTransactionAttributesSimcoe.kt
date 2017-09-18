package com.prolificinteractive.mparticle

import com.mparticle.commerce.TransactionAttributes
import com.prolificinteractive.simcoe.Properties

fun getTransactionAttributes(properties: Properties?): TransactionAttributes {
    val transaction = TransactionAttributes()
    transaction.affiliation = properties?.get(MPTransactionAttributesKeys.AFFILIATION.key)?.toString()
    transaction.couponCode = properties?.get(MPTransactionAttributesKeys.COUPON_CODE.key)?.toString()
    transaction.revenue = properties?.get(MPTransactionAttributesKeys.REVENUE.key) as Double
    transaction.shipping = properties?.get(MPTransactionAttributesKeys.SHIPPING.key) as Double
    transaction.tax = properties?.get(MPTransactionAttributesKeys.TAX.key) as Double
    transaction.id = properties?.get(MPTransactionAttributesKeys.TRANSACTION_ID.key)?.toString()

    return transaction
}

/// The MPTransactionAttributes keys available.
///
/// - affiliation: The affiliation.
/// - couponCode: The coupon code.
/// - revenue: The revenue amount.
/// - shipping: The shipping amount.
/// - tax: The tax amount.
/// - transactionId: The transaction Id.
enum class MPTransactionAttributesKeys(val key: String) {

    AFFILIATION("affiliation"),
    COUPON_CODE("couponCode"),
    REVENUE("revenue"),
    SHIPPING("shipping"),
    TAX("tax"),
    TRANSACTION_ID("transactionId");
}