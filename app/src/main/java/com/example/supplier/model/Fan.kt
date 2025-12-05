package com.example.supplier.model

data class Fan(
    val name: String,
    val sizeMm: Int,
    val rpm: Int,
    val rgbSupport: Boolean,
    val imageUrl: String
)
