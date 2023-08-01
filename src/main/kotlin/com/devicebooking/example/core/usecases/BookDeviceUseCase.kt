package com.devicebooking.example.core.usecases

import com.devicebooking.example.core.gateways.DeviceInfoDataGateway
import com.devicebooking.example.core.models.DeviceInfo
import jakarta.inject.Singleton
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class BookDeviceUseCase(
    private val deviceInfoDataGateway: DeviceInfoDataGateway
) {
    private val logger: Logger = LoggerFactory.getLogger(BookDeviceUseCase::class.java)

    @Transactional
    suspend fun execute(model: String, newAvailability: Boolean, bookedBy: String): Result<DeviceInfo?> {
        logger.info("Updating device availability to {} for model {}", newAvailability, model)
        val availableDevicesResult = deviceInfoDataGateway.getByModel(model)
        // no such device in the system
        val deviceList = availableDevicesResult.getOrNull()
        if (availableDevicesResult.isFailure || deviceList.isNullOrEmpty()) return Result.success(null)
        if (deviceList.count { it.isAvailable == !newAvailability } == 0) {
            // there are no available devices
            return Result.failure(Exception("There are no available devices for model $model"))
        }
        return deviceInfoDataGateway.updateDeviceAvailability(
            deviceList.first { it.isAvailable == !newAvailability },
            newAvailability,
            bookedBy
        )
    }
}
