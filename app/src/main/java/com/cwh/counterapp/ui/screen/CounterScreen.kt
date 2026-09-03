package com.cwh.counterapp.ui.screen


import androidx.compose.foundation.background
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cwh.counterapp.ui.components.CounterButton
import com.cwh.counterapp.ui.components.CounterComponent
import com.cwh.counterapp.ui.components.ResetButton
import com.cwh.counterapp.ui.theme.BackgroundLight
import com.cwh.counterapp.viewmodel.CounterViewModel

@Composable
fun CounterScreen(
    viewModel: CounterViewModel = viewModel()
) {

    val count by viewModel.count.collectAsState()
    val target = viewModel.target

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight),
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
        CounterComponent(count.toString())

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        // Count button
        CounterButton(
            onClick = {
                viewModel.increment()
            }
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // Target
        Text(
            text = "$count / $target",
            style = MaterialTheme.typography.titleMedium
        )

        if (count == target) {

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "Target Completed!",
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // Reset button
        ResetButton(
            onClick = {
                viewModel.reset()
            }
        )
    }
}