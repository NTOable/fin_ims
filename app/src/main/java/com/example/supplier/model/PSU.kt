package com.example.supplier.model

data class PSU(
    val name: String,
    val wattage: Int,
    val efficiencyRating: String,
    val modularity: String, // Full, Semi, Non
    val size: String, // ATX, SFX
    val pcieConnectors: Int,
    val imageUrl: String
)
