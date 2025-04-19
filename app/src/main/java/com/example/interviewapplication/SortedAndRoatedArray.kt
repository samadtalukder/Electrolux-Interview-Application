package com.example.interviewapplication

/*
* Given a sorted and rotated array arr[] of n distinct elements, the task is to find the index of given key in the array. If the key is not present in the array, return -1.
Examples:
Input: arr[] = [5, 6, 7, 8, 9, 10, 1, 2, 3], key = 3
Output: 8
Explanation: 3 is present at index 8 in arr[].
Input: arr[] = [3, 5, 1, 2], key = 6
Output: -1
Explanation: 6 is not present in arr[].
Input: arr[] = [33, 42, 72, 99], key = 42
Output: 1
Explanation: 42 is found at index 1.
*
*
* */

fun main() {
    val arr = listOf(5, 6, 7, 8, 9, 10, 1, 2, 3)
    val key = 3
    soterdAndRoatedArray(arr, key)

}

fun soterdAndRoatedArray(arr: List<Int>, key: Int) {}