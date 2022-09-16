package com.kotlinspring.service

import org.springframework.stereotype.Service

@Service
class GreetingService {

    fun retrieveGreeting(name: String, message: String) = "Hello $name, $message"
}