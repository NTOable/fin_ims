package com.example.supplier.model

data class CPU(
    val name: String,
    val cores: Int,
    val threads: Int,
    val baseClockGHz: Double,
    val boostClockGHz: Double,
    val tdpWatt: Int,
    val socket: String,
    val supportedMemoryType: String,
    val maxMemoryChannels: Int,
    val imageUrl: String
)

