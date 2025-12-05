package com.example.supplier.model

data class Case(
    val name: String,
    val formFactor: String,
    val maxGpuLengthMm: Int,
    val maxCoolingHeightMm: Int,
    val supportedPsuSize: String,
    val supportedFanSizes: List<Int>,
    val maxStorageBaySizeMm: Double,
    val imageUrl: String
)
