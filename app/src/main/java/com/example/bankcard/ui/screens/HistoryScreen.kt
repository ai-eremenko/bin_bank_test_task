package com.example.bankcard.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bankcard.presentation.history.HistoryEvent
import com.example.bankcard.presentation.history.HistoryViewModel
import com.example.bankcard.ui.components.HistoryList
import com.example.bankcard.ui.view.ErrorView
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = koinViewModel(),
    onNavigateBack: () -> Unit
) {
    val state = viewModel.state.value

    val events = remember {
        object {
            val onDeleteItem: (String) -> Unit = { bin ->
                viewModel.onEvent(HistoryEvent.DeleteItem(bin))
            }

            val onRetry: () -> Unit = {
                viewModel.onEvent(HistoryEvent.LoadHistory)
            }

            val onClearError: () -> Unit = {
                viewModel.onEvent(HistoryEvent.ClearError)
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onEvent(HistoryEvent.LoadHistory)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Search History") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                state.error != null -> {
                    ErrorView(
                        message = state.error,
                        onRetry = events.onRetry,
                    )
                }

                state.history.isEmpty() -> {
                    Text(
                        text = "No search history",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {
                    HistoryList(
                        history = state.history,
                        onDeleteItem = events.onDeleteItem,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    HistoryScreen(onNavigateBack = {})
}