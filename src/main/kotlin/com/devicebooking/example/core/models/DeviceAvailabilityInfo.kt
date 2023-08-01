package com.devicebooking.example.core.models

data class DeviceAvailabilityInfo(
    val model: String,
    val technology: String,
    val bands2G: String,
    val bands3G: String,
    val bands4G: String,
    val availableAmount: Int,
    val lastBookedBy: String? = null,
    val lastBookedAt: Long? = null
)
