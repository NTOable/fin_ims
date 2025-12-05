package com.example.supplier.model

data class Memory(
    val name: String,
    val capacityGb: Int,
    val type: String,
    val modules: Int,
    val speedMHz: Int,
    val imageUrl: String
)
