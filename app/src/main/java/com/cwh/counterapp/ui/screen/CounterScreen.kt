package com.cwh.counterapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CounterScreen() {

    var count by remember {
        mutableIntStateOf(0)
    }

    val target = 33


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // App title
        Text(
            text = "Tasbih Counter",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // Current Dhikr
        Text(
            text = "SubhanAllah",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        // Counter
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.displayLarge
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        // Count button
        Button(
            onClick = {
                if (count < target) {
                    count++
                }
            },
            modifier = Modifier.size(100.dp)
        ) {
            Text(
                text = "+"
            )
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // Target
        Text(
            text = "Target: $target"
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // Reset button
        OutlinedButton(
            onClick = {
                count = 0
            }
        ) {
            Text(
                text = "RESET"
            )
        }
    }
}