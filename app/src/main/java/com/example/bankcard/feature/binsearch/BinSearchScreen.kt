package com.example.bankcard.feature.binsearch

import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BinSearchScreen(
    viewModel: BinSearchViewModel = koinViewModel(),
    onNavigateToHistory: () -> Unit
) {

    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val events = remember {
        object {
            val onSearchQueryChanged: (String) -> Unit = { query ->
                viewModel.onEvent(BinSearchEvent.SearchQueryChanged(query))
            }
            val onSearch: () -> Unit = {
                viewModel.onEvent(BinSearchEvent.SearchButtonClicked)
            }
            val onCloseBottomSheet: () -> Unit = {
                viewModel.onEvent(BinSearchEvent.CloseBottomSheet)
            }
        }
    }

    BinSearchView(
        uiState = state,
        onSearchQueryChanged = events.onSearchQueryChanged,
        onSearch = events.onSearch,
        onNavigateToHistory = onNavigateToHistory,
        onCloseBottomSheet = events.onCloseBottomSheet
    )

    LaunchedEffect(state.errorResId) {
        state.errorResId?.let {
            Toast.makeText(context, context.getString(it), Toast.LENGTH_SHORT).show()
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private  fun BinSearchScreenPreview() {
    BinSearchScreen(onNavigateToHistory = {})
}