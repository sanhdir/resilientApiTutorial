package com.example.resilientApi.controller

import com.example.resilientApi.service.ServiceA
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/serviceA")
class ServiceAController(private val serviceA: ServiceA) {
    @GetMapping(value = ["/callServiceB"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun callServiceA(): String {
        return serviceA.callServiceB()
    }
}