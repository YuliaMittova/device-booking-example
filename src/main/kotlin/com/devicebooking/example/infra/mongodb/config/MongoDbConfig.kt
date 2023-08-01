package com.devicebooking.example.infra.mongodb.config

import io.micronaut.context.annotation.Value
import jakarta.inject.Singleton

@Singleton
class MongoDbConfig {
  @Value("\${micronaut.application.name}")
  lateinit var databaseName: String
}
