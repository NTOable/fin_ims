package com.example.supplier.model

data class Storage(
    val name: String,
    val capacityGb: Int,
    val type: String, // NVMe, SATA, HDD
    val sizeMm: Double,
    val interfaceType: String, // e.g. "M.2", "SATA"
    val imageUrl: String
)

