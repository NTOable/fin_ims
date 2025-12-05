package com.example.supplier.pcbuilderapp

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "inventory")
class InventoryItem (

    @PrimaryKey(autoGenerate= true) val id: Int= 0,
    val code: String,
    val name: String,
    val brand: String,
    val dateAdded: Long= System.currentTimeMillis()
    )