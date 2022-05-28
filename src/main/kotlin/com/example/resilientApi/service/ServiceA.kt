package com.example.resilientApi.service

import com.example.resilientApi.response.SearchResponse
import io.github.resilience4j.retry.annotation.Retry
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.time.LocalDateTime


@Service
class ServiceA(){
    private val serviceBUrl = "http://localhost:8085/serviceB/someMethod"
    private val serviceCUrl = "http://localhost:8085/serviceC/someMethod"
    private val serviceDUrl = "http://localhost:8085/serviceD/someMethod"
    private val log = KotlinLogging.logger {}

    @Retry(name = "callServiceBRetry", fallbackMethod = "fallback")
    fun callServiceB(): String {

        log.info { "ServiceA calling service $serviceBUrl at ${LocalDateTime.now()}" }
        val restTemplate = RestTemplate()
               return  restTemplate.getForObject(
                    serviceBUrl,
                    String::class
                )
    }

    private fun fallback(e: Exception?): String? {
        log.info { "calling fallback method" }
        return "fallback value"
    }

    @Retry(name = "callServiceCThrowingException")
    fun callServiceC(): String {
        log.info { "ServiceA calling service $serviceCUrl at ${LocalDateTime.now()}" }
        throw Exception()
    }

    @Retry(name = "callServiceDPredicate")
    fun callServiceD(): SearchResponse {
        val searchResponse = SearchResponse("","")
        log.info { "ServiceA calling service $serviceDUrl at ${LocalDateTime.now()}" }
        val restTemplate = RestTemplate()
            try {
                searchResponse.response =  restTemplate.getForObject(
                    serviceDUrl,
                    String::class
                )
                searchResponse.codeError = "200"

        } catch (e: Exception) {
                searchResponse.response = "Something's going wrong!"
                searchResponse.codeError = "500"
        }
        return searchResponse;
    }



}