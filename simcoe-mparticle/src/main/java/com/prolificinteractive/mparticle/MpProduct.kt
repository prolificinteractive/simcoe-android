package com.prolificinteractive.mparticle

import com.mparticle.commerce.Product
import com.prolificinteractive.simcoe.SimcoeProductConvertible


fun mpProduct(simcoeProductConvertible: SimcoeProductConvertible): Product {
    val simcoeProduct = simcoeProductConvertible.toSimcoeProduct()
    val product = Product.
            Builder(
                    simcoeProduct.productName,
                    simcoeProduct.productId,
                    simcoeProduct.price ?: 0.toDouble()
            )
            .quantity(simcoeProduct.quantity.toDouble())


    val properties = simcoeProduct.properties ?: return product.build()

    product.brand(properties[MPProductKeys.BRAND.key] as? String ?: "")
    product.category(properties[MPProductKeys.CATEGORY.key] as? String ?: "")
    product.couponCode(properties[MPProductKeys.COUPON_CODE.key] as? String ?: "")
    product.sku(properties[MPProductKeys.SKU.key] as? String ?: "")
    product.position(properties[MPProductKeys.POSITION.key] as? Int ?: 0)

    return product.build()
}


//
//  Simcoe
//
/// The MPProduct keys available for use with additionalProperties.
///
/// - brand: The product brand.
/// - category: A category to which the product belongs.
/// - couponCode: The coupon associated with the product.
/// - sku: The sku of the product.
/// - position: The position of the product on the screen or impression list.
/// - variant: The variant.
enum class MPProductKeys(val key: String) {
    BRAND("brand"),
    CATEGORY("category"),
    COUPON_CODE("couponCode"),
    SKU("sku"),
    POSITION("position");
}