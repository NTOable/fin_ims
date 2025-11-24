package com.example.supplier.data

import androidx.room.*

@Dao
interface InventoryDao {

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun addItem(item: InventoryItem)

    @Query("SELECT * FROM inventory ORDER BY dateAdded DESC")
    suspend fun getAllItems(): List<InventoryItem>

}