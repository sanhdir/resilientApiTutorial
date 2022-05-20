package com.example.resilientApi.service

import io.github.resilience4j.retry.annotation.Retry
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject


@Service
class ServiceA(){
    private val serviceBUrl = "http://localhost:8085/serviceB/someMethod"
    private val log = KotlinLogging.logger {}

    @Retry(name = "callServiceBRetry", fallbackMethod = "fallback")
    fun callServiceB(): String {

        log.info { "ServiceA calling service $serviceBUrl" }
        val restTemplate = RestTemplate()


               return  restTemplate.getForObject<String>(
                    serviceBUrl,
                    String::class
                )


    }

    fun fallback(e: Exception?): String? {
        log.info { "calling fallback method" }
        return "fallback value"
    }



}