package com.example.interviewapplication

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

interface CallbackApi {
    fun fetchData(callback: (String?, Throwable?) -> Unit)
}

val apiWrapper = ApiWrapper(object : CallbackApi {
    override fun fetchData(callback: (String?, Throwable?) -> Unit) {
        // Simulate an asynchronous call
        Thread.sleep(1000)
        callback("Hello, world!", null)
    }
})

class ApiWrapper(private val api: CallbackApi) {
    //Question1: Call fetchData method in apiWrapper on the main thread.

    private fun fetchData(): Flow<String> = flow {
        val apiData = withContext(Dispatchers.Main) {
            suspendCoroutine { ct ->
                api.fetchData { success, error ->
                    if (success != null) {
                        ct.resume(success)
                    } else if (error != null) {
                        ct.resumeWithException(error)
                    } else {
                        ct.resumeWithException(NullPointerException("Data null"))
                    }
                }
            }
        }
        emit(apiData)

        /*private fun fetchData(): Flow<String> = callbackFlow {
        api.fetchData { success, error ->
            if (error != null) {
                close(error)
            } else if (success != null) {
                trySend(success)
            }
            close()
        }
        awaitClose()
    }.flowOn(Dispatchers.Main)*/

    }

    suspend fun test() {
        //Question2: Call fetchData method above (which returns a flow) on a background thread.
        withContext(Dispatchers.IO) {
            apiWrapper.fetchData().collect { result ->
                Log.e("#", "Result: $result")
            }
        }

    }
}
