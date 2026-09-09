package com.cwh.counterapp.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ResetButton(
    onClick: () -> Unit
) {

    OutlinedButton(
        onClick = onClick
    ) {
        Text("RESET")
    }
}