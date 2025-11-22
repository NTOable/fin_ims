package com.example.supplier.data

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "inventory")
class InventoryItem (

    @PrimaryKey(autoGenerate= true) val id: Int= 0,
    val barcode: String,
    val name: String,
    val dateAdded: Long= System.currentTimeMillis()
    )