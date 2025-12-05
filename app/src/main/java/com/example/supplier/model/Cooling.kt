package com.example.supplier.model

data class Cooling(
    val name: String,
    val type: String, // Air or Liquid
    val heightMm: Int,
    val tdpSupportWatt: Int,
    val supportedSockets: List<String>,
    val imageUrl: String
)
