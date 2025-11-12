package com.example.bankcard.ui.screens

import BinSearchView
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.bankcard.presentation.binsearch.BinSearchEvent
import com.example.bankcard.presentation.binsearch.BinSearchViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BinSearchScreen(
    viewModel: BinSearchViewModel = koinViewModel(),
    onNavigateToHistory: () -> Unit
) {

    val state by viewModel.state.collectAsState()

    val events = remember {
        object {
            val onSearchQueryChanged: (String) -> Unit = { query ->
                viewModel.onEvent(BinSearchEvent.SearchQueryChanged(query))
            }

            val onSearch: () -> Unit = {
                viewModel.onEvent(BinSearchEvent.SearchBinInfo)
            }

            val onShowBottomSheet: (Boolean) -> Unit = { show ->
                viewModel.onEvent(BinSearchEvent.ShowBottomSheet(show))
            }
        }
    }

    BinSearchView(
        uiState = state,
        onSearchQueryChanged = events.onSearchQueryChanged,
        onSearch = events.onSearch,
        onNavigateToHistory = onNavigateToHistory,
        onShowBottomSheet = events.onShowBottomSheet
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BinSearchScreenPreview() {
    BinSearchScreen(onNavigateToHistory = {})
}