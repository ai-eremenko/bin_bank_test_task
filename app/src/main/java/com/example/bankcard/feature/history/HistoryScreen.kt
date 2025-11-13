package com.example.bankcard.feature.history

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bankcard.R
import com.example.bankcard.domain.model.BinInfo
import com.example.bankcard.feature.info_sheet.BinInfoBottomSheet
import com.example.bankcard.uikit.theme.Black
import com.example.bankcard.uikit.theme.White
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = koinViewModel(),
    onNavigateBack: () -> Unit
) {

    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val showBottomSheet = state.showBottomSheet
    val events = remember {
        object {
            val onDeleteItem: (String) -> Unit = { bin ->
                viewModel.onEvent(HistoryEvent.DeleteItem(bin))
            }
            val onItemClick: (BinInfo) -> Unit = { binInfo ->
                viewModel.onEvent(HistoryEvent.ShowBottomSheet(true, binInfo))
            }
            val onDismissBottomSheet: () -> Unit = {
                viewModel.onEvent(HistoryEvent.ShowBottomSheet(false))
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {  Text(stringResource(R.string.search_history))  },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back),
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
                state.history.isEmpty() -> {
                    Text(
                        text = stringResource(R.string.no_search_history),
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

    LaunchedEffect(Unit) {
        viewModel.onEvent(HistoryEvent.LoadHistory)
    }
    LaunchedEffect(state.errorResId) {
        state.errorResId?.let {
            Toast.makeText(context, context.getString(it), Toast.LENGTH_SHORT).show()
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun HistoryScreenPreview() {
    HistoryScreen(onNavigateBack = {})
}