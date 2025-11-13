package com.example.bankcard.uikit.components

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bankcard.R
import com.example.bankcard.domain.model.BinInfo
import com.example.bankcard.uikit.theme.White

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
            InfoRow(stringResource(R.string.country_label), binInfo.countryName)
            Spacer(modifier = Modifier.height(16.dp))

            InfoRow(stringResource(R.string.coordinates_label),
                if (binInfo.countryLatitude != null && binInfo.countryLongitude != null) {
                    "${binInfo.countryLatitude}, ${binInfo.countryLongitude}"
                } else null
            )
            Spacer(modifier = Modifier.height(16.dp))

            InfoRow(stringResource(R.string.card_type_label), binInfo.brand)
            Spacer(modifier = Modifier.height(16.dp))

            InfoRow(stringResource(R.string.bank_name_label), binInfo.bankName)
            Spacer(modifier = Modifier.height(16.dp))

            InfoRow(stringResource(R.string.bank_url_label), binInfo.bankUrl)
            Spacer(modifier = Modifier.height(16.dp))

            InfoRow(stringResource(R.string.bank_phone_label), binInfo.bankPhone)
            Spacer(modifier = Modifier.height(16.dp))

            InfoRow(stringResource(R.string.city_label), binInfo.bankCity)
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
            text = value ?: stringResource(R.string.not_available),
            style = MaterialTheme.typography.bodySmall
        )
    }
}