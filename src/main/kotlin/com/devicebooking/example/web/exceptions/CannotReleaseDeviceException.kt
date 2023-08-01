package com.devicebooking.example.web.exceptions

class CannotReleaseDeviceException(
    override val message: String? = "Releasing device failed",
    override val cause: Throwable? = null,
) : RuntimeException(
    message,
    cause,
)
