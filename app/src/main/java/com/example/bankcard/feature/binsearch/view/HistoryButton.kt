package com.example.bankcard.feature.binsearch.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bankcard.R
import com.example.bankcard.uikit.theme.Gray
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun HistoryButton(
    onNavigateToHistory: () -> Unit,
    onCloseBottomSheet: () -> Unit,
    showBottomSheet: Boolean
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                if (showBottomSheet) {
                    onCloseBottomSheet()
                    CoroutineScope(Dispatchers.Main).launch {
                        onNavigateToHistory()
                    }
                } else {
                    onNavigateToHistory()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Gray
            )
        ) {
            Text(
                text = stringResource(R.string.history),
                style = MaterialTheme.typography.headlineLarge,
            )
        }
    }
}