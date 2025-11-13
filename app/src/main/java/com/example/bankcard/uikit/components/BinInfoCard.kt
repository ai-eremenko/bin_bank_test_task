package com.example.bankcard.uikit.components

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bankcard.R
import com.example.bankcard.domain.model.BinInfo
import com.example.bankcard.uikit.theme.White
import androidx.core.net.toUri

@Composable
fun BinInfoCard(
    binInfo: BinInfo,
    modifier: Modifier = Modifier
) {
    val activity = LocalContext.current as AppCompatActivity
    val bankCoordinates = if (binInfo.countryLatitude != null && binInfo.countryLongitude != null) {
        "${binInfo.countryLatitude}, ${binInfo.countryLongitude}"
    } else null
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
            InfoRow(
                label = stringResource(R.string.country_label),
                value = binInfo.countryName
            )
            Spacer(modifier = Modifier.height(16.dp))
            InfoRow(
                label = stringResource(R.string.coordinates_label),
                value = bankCoordinates,
                isClickable = bankCoordinates != null,
                onClick = {
                    val lat = binInfo.countryLatitude ?: return@InfoRow
                    val lng = binInfo.countryLongitude ?: return@InfoRow
                    activity.processLocationIntent(
                        latitude = lat,
                        longitude = lng
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            InfoRow(
                stringResource(R.string.card_type_label),
                binInfo.brand
            )
            Spacer(modifier = Modifier.height(16.dp))
            InfoRow(
                stringResource(R.string.bank_name_label),
                binInfo.bankName
            )
            Spacer(modifier = Modifier.height(16.dp))
            InfoRow(
                stringResource(R.string.bank_url_label),
                binInfo.bankUrl,
                isClickable = binInfo.bankUrl.isNullOrEmpty().not(),
                onClick = {
                    val url = binInfo.bankUrl ?: return@InfoRow
                    activity.processUrlIntent(url)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            InfoRow(
                stringResource(R.string.bank_phone_label),
                binInfo.bankPhone,
                isClickable = binInfo.bankPhone.isNullOrEmpty().not(),
                onClick = {
                    val phone = binInfo.bankPhone ?: return@InfoRow
                    activity.processPhoneIntent(phone)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            InfoRow(
                stringResource(R.string.city_label),
                binInfo.bankCity
            )
        }
    }
}

@Composable
private fun InfoRow(
    label: String,
    value: String?,
    isClickable: Boolean = false,
    onClick: () -> Unit = {}
) {
    Column(modifier = Modifier.clickable(enabled = isClickable, onClick = onClick)) {
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

private fun Activity.processUrlIntent(url: String) {
    runCatching {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        startActivity(intent)
    }
}

private fun Activity.processPhoneIntent(phoneNumber: String) {
    runCatching {
        val intent = Intent(Intent.ACTION_DIAL, "tel:$phoneNumber".toUri())
        startActivity(intent)
    }
}

private fun Activity.processLocationIntent(latitude: Double, longitude: Double) {
    try {
        val uri = "geo:$latitude,$longitude?q=$latitude,$longitude".toUri()
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    } catch (e: Exception) {
        val alternativeUri = "https://maps.google.com/?q=$latitude,$longitude".toUri()
        val alternativeIntent = Intent(Intent.ACTION_VIEW, alternativeUri)
        startActivity(alternativeIntent)
    }
}