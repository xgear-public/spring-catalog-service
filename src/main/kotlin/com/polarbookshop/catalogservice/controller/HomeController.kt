package com.polarbookshop.catalogservice.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @GetMapping("/")
    fun getGreetings(): String {
        return "Welcome to the Polar book catalog service!"
    }
}