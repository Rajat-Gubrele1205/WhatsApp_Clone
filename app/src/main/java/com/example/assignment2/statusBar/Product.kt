package com.example.assignment2.statusBar

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @ColumnInfo(name = "product_id")
    val productID: String,
    val checkoutURL: String,
    val title: String,
    val price: String,
    val description: String,
    val shippingInfo: String,
    val discountedPrice: String,
    val imageURL: String,
    val buttonText: String,
    val outOfStock: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0
)
//@Entity
//data class ProductsEntity(
//    val monitor: List<ProductEntity>,
//    val monitorPro: List<ProductEntity>,
//    val replacementMonitor: List<ProductEntity>,
//    val transmissiveStrip: List<ProductEntity>,
//    val reflectiveStrip: List<ProductEntity>,
//    val reflective3TStrip: List<ProductEntity>,
//    val clip: List<ProductEntity>
//)
//@Entity
//data class InformationEntity(
//    val shopMessage: String
//)
//
//@Entity
//data class ProductResponseEntity(
//    val products: ProductsEntity,
//    val information: InformationEntity
//)

