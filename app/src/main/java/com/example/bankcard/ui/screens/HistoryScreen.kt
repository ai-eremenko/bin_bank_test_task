package com.example.bankcard.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bankcard.presentation.history.HistoryEvent
import com.example.bankcard.presentation.history.HistoryViewModel
import com.example.bankcard.ui.components.BinInfoBottomSheet
import com.example.bankcard.ui.components.HistoryList
import com.example.bankcard.ui.theme.Black
import com.example.bankcard.ui.theme.White
import com.example.bankcard.ui.view.ErrorView
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = koinViewModel(),
    onNavigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    val showBottomSheet = state.showBottomSheet

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

            val onItemClick: (com.example.bankcard.domain.model.BinInfo) -> Unit = { binInfo ->
            viewModel.onEvent(HistoryEvent.ShowBottomSheet(true, binInfo))
            }

            val onDismissBottomSheet: () -> Unit = {
            viewModel.onEvent(HistoryEvent.ShowBottomSheet(false))
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
                            contentDescription = "Back",
                            tint = Black
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = White
        ) {
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
                    state.error?.let { errorMessage ->
                        ErrorView(
                            message = errorMessage,
                            onRetry = events.onRetry,
                        )
                    }
                }

                state.history.isEmpty() -> {
                    Text(
                        text = "No search history",
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }

                else -> {
                    HistoryList(
                        history = state.history,
                        onDeleteItem = events.onDeleteItem,
                        onItemClick = events.onItemClick,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            if (showBottomSheet) {
                BinInfoBottomSheet(
                    binInfo = state.selectedBinInfo,
                    onDismiss = events.onDismissBottomSheet
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