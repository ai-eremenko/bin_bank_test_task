package com.example.bankcard.ui.screens

import BinSearchView
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.bankcard.presentation.binsearch.BinSearchViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BinSearchScreen(
    viewModel: BinSearchViewModel = koinViewModel()
) {

    BinSearchView(
        uiState = TODO(),
        searchQuery = TODO(),
        onSearchQueryChanged = TODO(),
        onInfoClick = TODO(),
        loadBinInfo = TODO()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BinSearchScreenPreview() {

}