package com.example.supplier.model

data class GPU(
    val name: String,
    val vramGb: Int,
    val powerDrawWatt: Int,
    val lengthMm: Int,
    val requiredPcieVersion: Int,
    val requiredPcieConnectors: Int,
    val imageUrl: String
)
