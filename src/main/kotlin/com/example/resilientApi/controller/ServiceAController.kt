package com.example.resilientApi.controller

import com.example.resilientApi.response.SearchResponse
import com.example.resilientApi.service.ServiceA
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/serviceA")
class ServiceAController(private val serviceA: ServiceA) {
    @GetMapping(value = ["/callServiceB"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun callServiceB(): String {
        return serviceA.callServiceB()
    }

    @GetMapping(value = ["/callServiceC"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun callServiceC(): String {
        return serviceA.callServiceC()
    }

    @GetMapping(value = ["/callServiceD"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun callServiceD(): SearchResponse {
        return serviceA.callServiceD()
    }
}