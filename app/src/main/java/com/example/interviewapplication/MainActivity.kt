package com.example.interviewapplication

import android.os.Bundle
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

                    var resultOne by remember { mutableStateOf(0) }
                    var resultTwo by remember { mutableStateOf(0) }

                    LaunchedEffect(true) {
                        launch(Dispatchers.Default) { resultOne = taskOne() }
                        launch(Dispatchers.Default) { resultTwo = taskTwo() }
                    }

                    ShowTaskData(resultOne, resultTwo)

                }
            }
        }
    }

    @Composable
    fun ShowTaskData(resultOne: Int, resultTwo: Int) {
        Column {
            Text("TaskOne Result: $resultOne")
            Text("TaskTwo Result: $resultTwo")
        }
    }


    private suspend fun taskOne(): Int {
        delay(2000)
        return 10
    }

    private suspend fun taskTwo(): Int {
        delay(2000)
        return 10
    }
}
