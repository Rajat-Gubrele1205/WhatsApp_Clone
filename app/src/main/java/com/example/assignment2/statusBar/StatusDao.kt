package com.example.assignment2.statusBar


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.DeleteTable
import androidx.room.Query

import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface StatusDao {
    @Query("SELECT * FROM products")
    fun getALlProducts() : Flow<List<ProductEntity>>

    @Upsert
    suspend fun upsertEntity(productEntity: ProductEntity)

    @Query("DELETE FROM products")
    suspend fun deleteEntity()

}