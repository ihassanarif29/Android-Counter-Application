package com.cwh.counterapp.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CounterButton(
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        modifier = Modifier.size(100.dp),
        shape = CircleShape
    ) {
        Text(
            text = "+",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}