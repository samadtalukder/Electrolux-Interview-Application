package com.example.interviewapplication

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val composeTest = createComposeRule()


    @Test
    fun showTaskData() {
        // initialize value
        val taskOne = 10
        val taskTwo = 10
        val taskResult = 40

        // setup composable test values
        composeTest.setContent {
            ShowTaskData(taskOne, taskTwo, taskResult)
        }

        //
        composeTest
            .onNodeWithTag("taskOneText")
            .assertTextEquals("TaskOne: $taskOne")

        composeTest
            .onNodeWithTag("taskTwoText")
            .assertTextEquals("TaskTwo: $taskTwo")

        composeTest
            .onNodeWithTag("resultText")
            .assertTextEquals("Result: $taskResult")
    }

    @Test
    fun testLaunchedEffect() {
        val taskOne = 5
        val taskTwo = 7

        composeTest.setContent {
            var result by remember { mutableStateOf(0) }
            var taskOneState by remember { mutableStateOf(0) }
            var taskTwoState by remember { mutableStateOf(0) }

            LaunchedEffect(Unit) {
                taskOneState = taskOne
                taskTwoState = taskTwo
                result = taskOneState + taskTwoState
            }
            ShowTaskData(taskOne, taskTwo, result)
        }

        composeTest.waitForIdle()

        composeTest
            .onNodeWithTag("taskOneText")
            .assertTextEquals("TaskOne: $taskOne")

        composeTest
            .onNodeWithTag("taskTwoText")
            .assertTextEquals("TaskTwo: $taskTwo")

        composeTest
            .onNodeWithTag("resultText")
            .assertTextEquals("Result: ${taskOne + taskTwo}")
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testExists() = runComposeUiTest {
        val taskOne = 0
        val taskTwo = 0
        val result = 10
        setContent {
            ShowTaskData(resultOne = 0, resultTwo = 0, result = 10)
        }

        onNodeWithTag("taskOneText").assertTextContains("0")
        onNodeWithTag("taskTwoText").assertTextContains("0")
        onNodeWithTag("resultText").assertTextContains("10")
    }
}