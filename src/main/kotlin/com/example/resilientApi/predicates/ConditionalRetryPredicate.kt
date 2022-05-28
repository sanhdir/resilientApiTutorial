package com.example.resilientApi.predicates

import com.example.resilientApi.response.SearchResponse
import mu.KotlinLogging
import java.util.function.Predicate


class ConditionalRetryPredicate : Predicate<SearchResponse> {
    private val log = KotlinLogging.logger {}

    override fun test(searchResponse: SearchResponse): Boolean {
        log.info { "ConditionalRetryPredicate: testing condition on ${searchResponse.codeError} " }
        if (!searchResponse.codeError.isNullOrEmpty()) {
            return searchResponse.codeError == ("500");
        }
        return false;
    }
}


