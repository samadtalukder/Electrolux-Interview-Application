package com.example.interviewapplication

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

/*
* Create a function that runs two API calls in parallel.
* If an API call takes more than 2 seconds, return null instead of failing.
* If an API call throws an exception, immediately propagate the error.
* */

suspend fun fetchInParallel(): Pair<String?, String> = coroutineScope {
    val first = async {
        withTimeoutOrNull(2000) {
            fetchFirstData()
        }
    }
    val second = async {
        fetchSecondData()
    }

    Pair(first.await(), second.await())
}

fun fetchFirstData(): String {
    return "first data"
}

fun fetchSecondData(): String {
    return "second data"
}

fun main() {
    runBlocking {
        println(fetchInParallel())
    }
}

