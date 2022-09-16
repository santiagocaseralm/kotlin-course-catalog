package com.kotlinspring.controller

import com.kotlinspring.service.GreetingService
import mu.KLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/greetings")
class GreetingController(val greetingService: GreetingService) {

    @Value("\${message}")
    lateinit var message: String

    companion object: KLogging()

    @GetMapping("/{name}")
    fun retrieveGreeting(@PathVariable("name") name: String): String {
        logger.info("Name is $name")
        return greetingService.retrieveGreeting(name, message)
    }
}