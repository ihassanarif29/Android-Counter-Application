package com.cwh.counterapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cwh.counterapp.data.local.TasbihDatabase
import com.cwh.counterapp.data.repository.HistoryRepository
import com.cwh.counterapp.ui.components.HistoryItem
import com.cwh.counterapp.viewmodel.HistoryViewModel
import com.cwh.counterapp.viewmodel.HistoryViewModelFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HistoryScreen() {

    val context = LocalContext.current

    val database =
        TasbihDatabase.getDatabase(context)

    val repository =
        HistoryRepository(database.historyDao())

    val factory =
        HistoryViewModelFactory(repository)

    val viewModel: HistoryViewModel =
        viewModel(factory = factory)

    val history by viewModel.history
        .collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Dhikr History",
            style = MaterialTheme.typography.headlineMedium
        )

        if (history.isEmpty()) {

            Text(
                text = "No completed Dhikr yet.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 16.dp)
            )

        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = history,
                    key = { it.id }
                ) { item ->

                    HistoryItem(item)
                }
            }
        }
    }
}