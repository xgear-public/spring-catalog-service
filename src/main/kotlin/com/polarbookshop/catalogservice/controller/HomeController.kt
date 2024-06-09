package com.polarbookshop.catalogservice.controller

import com.polarbookshop.catalogservice.config.PolarProperties
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController(
    private val polarProperties: PolarProperties,
) {

    @GetMapping("/")
    fun getGreetings(): String {
        return polarProperties.greeting
    }
}