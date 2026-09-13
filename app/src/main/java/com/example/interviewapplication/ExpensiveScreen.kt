package com.example.interviewapplication

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.collections.set

@Composable
fun ExpensiveList(modifier: Modifier = Modifier) {
    // Create the items list only once when the composable enters composition
    val items = remember { List(1000) { "Item $it" } }

    // Lift state up to maintain counter values across recycling
    // Use a mutable state map to store counter values for each item
    val counters = remember { mutableStateMapOf<String, Int>() }

    LazyColumn(modifier = modifier) {
        items(
            items = items,
            key = { item -> item }
        ) { item ->
            // Get current counter value from the map, defaulting to 0 if not present
            val counter = counters.getOrDefault(item, 0)

            ExpensiveItem(
                item = item,
                counter = counter,
                onIncrement = { counters[item] = counter + 1 }
            )
        }
    }
}

@Composable
fun ExpensiveItem(
    item: String,
    counter: Int,
    onIncrement: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = item,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )

        Button(onClick = onIncrement) {
            Text("Click $counter")
        }
    }
}