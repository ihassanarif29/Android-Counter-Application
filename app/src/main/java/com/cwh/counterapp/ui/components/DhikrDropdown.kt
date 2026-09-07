package com.cwh.counterapp.ui.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.cwh.counterapp.model.Dhikr

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DhikrDropdown(
    selectedDhikr: Dhikr,
    dhikrList: List<Dhikr>,
    onDhikrSelected: (Dhikr) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {

        TextField(
            value = selectedDhikr.name,
            onValueChange = {},
            readOnly = true,
            label = {
                Text("Select Dhikr")
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            modifier = Modifier.menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {

            dhikrList.forEach { dhikr ->

                DropdownMenuItem(
                    text = {
                        Text(dhikr.name)
                    },
                    onClick = {

                        onDhikrSelected(dhikr)

                        expanded = false
                    }
                )
            }
        }
    }
}