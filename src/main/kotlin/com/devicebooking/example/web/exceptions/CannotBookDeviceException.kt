package com.devicebooking.example.web.exceptions

class CannotBookDeviceException(
    override val message: String? = "Booking device failed",
    override val cause: Throwable? = null,
) : RuntimeException(
    message,
    cause,
)
