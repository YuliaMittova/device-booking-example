package com.devicebooking.example.web.controllers

import com.devicebooking.example.core.models.DeviceAvailabilityInfo
import com.devicebooking.example.core.models.DeviceInfo
import com.devicebooking.example.core.usecases.BookDeviceUseCase
import com.devicebooking.example.core.usecases.GetDeviceInfoUseCase
import com.devicebooking.example.web.exceptions.CannotBookDeviceException
import com.devicebooking.example.web.exceptions.CannotReleaseDeviceException
import com.devicebooking.example.web.exceptions.EntityNotFoundException
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory

@Controller("/api")
class DeviceBookingController(
    private val getDeviceInfoUseCase: GetDeviceInfoUseCase,
    private val bookDeviceUseCase: BookDeviceUseCase
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Get(uri = "bookDevice", produces = ["application/json"]) // add info for lastUpdatedAt abd lastUpdatedBy
    suspend fun bookDevice(
        model: String,
        bookedBy: String
    ): HttpResponse<DeviceInfo?> {
        logger.info("Received request to book device {}", model)
        val result = bookDeviceUseCase.execute(model, false, bookedBy)
        val deviceInfo = result.getOrNull()
        if (result.isFailure) {
            val errorMessage = result.exceptionOrNull()?.message ?: "Booking device model $model failed"
            throw CannotBookDeviceException(errorMessage)
        }
        if (result.isSuccess && deviceInfo == null) throw EntityNotFoundException("Device info for model $model not found")
        return HttpResponse.ok(deviceInfo)
    }

    @Get(uri = "releaseDevice", produces = ["application/json"])
    suspend fun releaseDevice(
        model: String,
        bookedBy: String
    ): HttpResponse<DeviceInfo?> {
        logger.info("Received request to release device {}", model)
        val result = bookDeviceUseCase.execute(model, true, bookedBy)
        val deviceInfo = result.getOrNull()
        if (result.isFailure) {
            val errorMessage = result.exceptionOrNull()?.message ?: "Releasing device model $model failed"
            throw CannotReleaseDeviceException(errorMessage)
        }
        if (result.isSuccess && deviceInfo == null) throw EntityNotFoundException("Device info for model $model not found")
        return HttpResponse.ok(deviceInfo)
    }

    @Get(uri = "deviceInfo", produces = ["application/json"])
    @Transactional
    suspend fun getDeviceInfo(model: String): HttpResponse<DeviceAvailabilityInfo> {
        logger.info("Received request to get device info for model {} received", model)
        val result = getDeviceInfoUseCase.execute(model = model)

        val deviceAvailabilityInfo = result.getOrNull()
        if (result.isFailure) throw EntityNotFoundException("Device info for model $model not found")
        return HttpResponse.ok(deviceAvailabilityInfo)
    }
}
