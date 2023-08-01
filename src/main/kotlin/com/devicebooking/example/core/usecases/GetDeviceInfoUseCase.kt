package com.devicebooking.example.core.usecases

import com.devicebooking.example.core.gateways.DeviceInfoDataGateway
import com.devicebooking.example.core.models.DeviceAvailabilityInfo
import jakarta.inject.Singleton
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class GetDeviceInfoUseCase(
    private val deviceInfoDataGateway: DeviceInfoDataGateway
) {
    private val logger: Logger = LoggerFactory.getLogger(BookDeviceUseCase::class.java)

    @Transactional
    suspend fun execute(model: String): Result<DeviceAvailabilityInfo> {
        logger.info("Getting device info for model {}", model)
        val foundDevices = deviceInfoDataGateway.getByModel(model).getOrNull()
        if (foundDevices.isNullOrEmpty()) return Result.failure(Exception("No devices found"))
        val availableAmount = foundDevices.count { it.isAvailable }
        val lastBookedDevice = foundDevices.sortedBy { it.lastBookedAt }.last()
        return Result.success(
            DeviceAvailabilityInfo(
                model = model,
                technology = lastBookedDevice.technology,
                bands2G = lastBookedDevice.bands2G,
                bands3G = lastBookedDevice.bands3G,
                bands4G = lastBookedDevice.bands4G,
                availableAmount = availableAmount,
                lastBookedBy = lastBookedDevice.lastBookedBy,
                lastBookedAt = lastBookedDevice.lastBookedAt,
            )
        )
    }
}
