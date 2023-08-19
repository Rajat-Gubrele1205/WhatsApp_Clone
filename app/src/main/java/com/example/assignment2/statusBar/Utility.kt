package com.example.assignment2.statusBar


import ProductsData
import android.util.Log

fun setAllData(products:ProductsData):List<ProductEntity>{
    val list = listOf(
        products.monitor,
        products.monitorPro,
        products.clip,
        products.reflectiveStrip,
        products.reflective3TStrip,
        products.replacementMonitor,
        products.transmissiveStrip
    )
    val result: MutableList<ProductEntity> = mutableListOf()
    list.forEach{
        it.forEach{product->
            Log.d("CheckAPi", "inside ")
            result.add(
                ProductEntity(
                    productID = product.productId,
                    checkoutURL = product.checkoutUrl,
                    title = product.title,
                    description = product.description,
                    price = product.price,
                    shippingInfo = product.shippingInfo,
                    discountedPrice = product.discountedPrice,
                    imageURL = product.imageUrl,
                    buttonText = product.buttonText,
                    outOfStock = product.outOfStock
                )
            )
        }
    }
    return result
}