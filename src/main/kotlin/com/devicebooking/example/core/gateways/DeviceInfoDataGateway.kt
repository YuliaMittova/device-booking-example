package com.devicebooking.example.core.gateways

import com.devicebooking.example.core.models.DeviceInfo

interface DeviceInfoDataGateway {

    suspend fun getByModel(model: String): Result<List<DeviceInfo>>

    // if isTaken - device will be marked as booked, otherwise it will be released
    suspend fun updateDeviceAvailability(
        deviceInfo: DeviceInfo,
        newAvailability: Boolean,
        updatedBy: String
    ): Result<DeviceInfo?>
}
