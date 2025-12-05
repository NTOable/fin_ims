package com.example.supplier.model

data class Motherboard(
    val name: String,
    val socket: String,
    val formFactor: String,
    val ramType: String,
    val maxMemoryGb: Int,
    val ramSlots: Int,
    val supportsPcieVersion: Int,
    val storageInterfaces: List<String>, // e.g. ["NVMe", "SATA"]
    val supportedFanHeaders: Int,
    val imageUrl: String
)

