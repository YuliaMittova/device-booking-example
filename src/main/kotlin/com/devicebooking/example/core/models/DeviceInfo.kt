package com.devicebooking.example.core.models

data class DeviceInfo(
    val id: String? = null,
    val model: String,
    val technology: String,
    val bands2G: String,
    val bands3G: String,
    val bands4G: String,
    val lastBookedBy: String?,
    val lastBookedAt: Long?,
    val isAvailable: Boolean = true
)
