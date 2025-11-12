package com.example.bankcard.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bankcard.domain.model.BinInfo
import com.example.bankcard.ui.theme.White

@Composable
fun BinInfoCard(
    binInfo: BinInfo,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            InfoRow("Country", binInfo.countryName)
            Spacer(modifier = Modifier.height(16.dp))

            InfoRow("Coordinates",
                if (binInfo.countryLatitude != null && binInfo.countryLongitude != null) {
                    "${binInfo.countryLatitude}, ${binInfo.countryLongitude}"
                } else null
            )
            Spacer(modifier = Modifier.height(16.dp))

            InfoRow("Card Type", binInfo.brand)
            Spacer(modifier = Modifier.height(16.dp))

            InfoRow("Bank name", binInfo.bankName)
            Spacer(modifier = Modifier.height(16.dp))

            InfoRow("Bank url", binInfo.bankUrl)
            Spacer(modifier = Modifier.height(16.dp))

            InfoRow("Bank phone", binInfo.bankPhone)
            Spacer(modifier = Modifier.height(16.dp))

            InfoRow("City", binInfo.bankCity)
        }
    }
}

@Composable
private fun InfoRow(
    label: String,
    value: String?
) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = value ?: "Not available",
            style = MaterialTheme.typography.bodySmall
        )
    }
}