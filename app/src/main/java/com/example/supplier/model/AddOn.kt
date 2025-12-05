package com.example.supplier.model

data class AddOn(
    val name: String,
    val interfaceType: String, // PCIe, USB
    val requiresSlot: Boolean,
    val imageUrl: String
)
