package com.devicebooking.example

import com.devicebooking.example.infra.mongodb.config.initMongoDbIndexes
import io.micronaut.runtime.Micronaut.build
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info

@OpenAPIDefinition(
    info = Info(
        title = "device-booking-service",
        version = "1.0",
        description = "Device Booking Service",
    ),
)
object Api

fun main(args: Array<String>) {
    build()
        .banner(false)
        .args(*args)
        .packages("com.devicebooking.example")
        .start()
        .initMongoDbIndexes()
}
