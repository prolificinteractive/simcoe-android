package com.prolificinteractive.mparticle

import com.mparticle.commerce.CommerceEvent
import com.mparticle.commerce.Product
import com.prolificinteractive.simcoe.Properties


fun getCommerceEvent(action: String, products: Array<Product>, eventProperties: Properties?): CommerceEvent {
    val product = products[0]
    val productList: MutableList<Product> = products.copyOfRange(1, products.size).toMutableList()
    val event = CommerceEvent.Builder(action, product)
    event.products(productList)

    val checkoutOptions = eventProperties?.get(MPCommerceEventKeys.CHECKOUT_OPTIONS.key).toString()
    val checkoutStep = eventProperties?.get(MPCommerceEventKeys.CHECKOUT_STEP.key) as Int
    val currency = eventProperties.get(MPCommerceEventKeys.CURRENCY.key).toString()
    val nonInteractive = eventProperties.get(MPCommerceEventKeys.NON_INTERACTIVE.key) as Boolean
    //TODO what to do with promotionContainer?
    val productListName = eventProperties.get(MPCommerceEventKeys.PRODUCT_LIST_NAME.key).toString()
    val productListSource = eventProperties.get(MPCommerceEventKeys.PRODUCT_LIST_SOURCE.key).toString()
    val screenName = eventProperties.get(MPCommerceEventKeys.SCREEN_NAME.key).toString()
    val transaction = getTransactionAttributes(eventProperties)

    return event
            .checkoutOptions(checkoutOptions)
            .checkoutStep(checkoutStep)
            .currency(currency)
            .nonInteraction(nonInteractive)
            .productListName(productListName)
            .productListSource(productListSource)
            .screen(screenName)
            .transactionAttributes(transaction)
            .build()
}

//
//  MPCommerceEventKeys.swift
//  Simcoe
//
/// The MPCommerceEvent keys available to use with eventProperties.
///
/// - checkoutOptions: The checkout options.
/// - checkoutStep: The checkout step.
/// - currency: The currency.
/// - nonInteractive: The non interactive flag.
/// - promotionContainer: The promotion container.
/// - productListName: The product list name.
/// - productListSource: The product list source.
/// - screenName: The screen name.
/// - transactionAttributes: The transactions attributes.
enum class MPCommerceEventKeys(val key: String) {
    CHECKOUT_OPTIONS("checkoutOptions"),
    CHECKOUT_STEP("checkoutStep"),
    CURRENCY("currency"),
    NON_INTERACTIVE("nonInteractive"),
    PROMOTION_CONTAINER("promotionContainer"),
    PRODUCT_LIST_NAME("productListName"),
    PRODUCT_LIST_SOURCE("productListSource"),
    SCREEN_NAME("screenName"),
    TRANSACTION_ATTRIBUTES("transactionAttributes");
}