package com.devicebooking.example.web.controllers

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller
class ApplicationController {

  @Get(uri = "health_check", processes = ["text/plain"])
  fun healthCheck(): String = "I'm healthy"
}
