package com.example.bankcard.feature.binsearch

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.bankcard.feature.binsearch.view.BinSearchTopAppBar
import com.example.bankcard.feature.binsearch.view.HistoryButton
import com.example.bankcard.feature.binsearch.view.SearchButton
import com.example.bankcard.feature.binsearch.view.SearchTextField
import com.example.bankcard.uikit.components.BinInfoBottomSheet
import com.example.bankcard.uikit.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BinSearchView(
    uiState: BinSearchState,
    onSearchQueryChanged: (String) -> Unit,
    onSearch: () -> Unit,
    onNavigateToHistory: () -> Unit,
    onCloseBottomSheet: () -> Unit
) {

    val focusManager = LocalFocusManager.current

    if (uiState.showBottomSheet) {
        BinInfoBottomSheet(
            binInfo = uiState.binInfo,
            onDismiss = {
                onCloseBottomSheet()
            }
        )
    }
    Scaffold(
        topBar = {
            BinSearchTopAppBar()
        },
        containerColor = White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            SearchTextField(
                searchQuery = uiState.searchQuery,
                onSearchQueryChanged = onSearchQueryChanged,
                onSearch = onSearch
            )
            Spacer(modifier = Modifier.height(16.dp))
            SearchButton(
                onSearch = {
                    focusManager.clearFocus()
                    onSearch()
                },
                isLoading = uiState.isLoading
            )
            Spacer(modifier = Modifier.height(8.dp))
            HistoryButton(
                onNavigateToHistory = onNavigateToHistory,
                showBottomSheet = uiState.showBottomSheet,
                onCloseBottomSheet = onCloseBottomSheet
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
