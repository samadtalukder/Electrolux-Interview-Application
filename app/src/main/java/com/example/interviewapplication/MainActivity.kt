package com.example.interviewapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.interviewapplication.ui.theme.InterviewApplicationTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            InterviewApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Question3: Call taskOne and taskTwo methods below in a way that they run in parallel.
                    var result by remember { mutableStateOf(0) }
                    var task1 by remember { mutableStateOf(0) }
                    var task2 by remember { mutableStateOf(0) }

                    LaunchedEffect(Unit) {
                        task1 = async { taskOne() }.await()
                        task2 = async { taskTwo() }.await()
                        result = task1 + task2

                        apiWrapper.test()
                    }

                    ShowTaskData(resultOne = task1, resultTwo = task2, result = result)
                }
            }
        }
    }

    @Composable
    fun ShowTaskData(resultOne: Int, resultTwo: Int, result: Int) {
        Column {
            Text("TaskOne: $resultOne")
            Text("TaskTwo: $resultTwo")
            Text("Result: $result")
        }
    }


    private suspend fun taskOne(): Int {
        return withContext(Dispatchers.Default) {
            delay(2000)
            return@withContext 10
        }
    }

    private suspend fun taskTwo(): Int {
        return withContext(Dispatchers.Default) {
            delay(2000)
            return@withContext 10
        }
    }

    private suspend fun getApiResult(){
        Log.e("","apiResult: ${apiWrapper.test()}")
    }

}
