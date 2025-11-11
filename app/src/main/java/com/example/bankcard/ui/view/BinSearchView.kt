import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bankcard.R
import com.example.bankcard.domain.model.BinInfo
import com.example.bankcard.presentation.binsearch.SearchState
import com.example.bankcard.ui.components.LoadingIndicator
import com.example.bankcard.ui.theme.AccentBrand
import com.example.bankcard.ui.theme.Black
import com.example.bankcard.ui.theme.Gray
import com.example.bankcard.ui.theme.White
import com.example.bankcard.ui.view.ErrorView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BinSearchView(
    uiState: SearchState,
    searchQuery: String,
    binInfo: BinInfo,
    onSearchQueryChanged: (String) -> Unit,
    onInfoClick: (Int) -> Unit,
    loadBinInfo: () -> Unit
) {
    Scaffold(
        topBar = {
            Column {
                Spacer(modifier = Modifier.height(12.dp))
                TopAppBar(
                    title = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(24.dp),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Text(
                                "BIN card search",
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = White,
                        titleContentColor = Black
                    )
                )
            }
        },
        containerColor = White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(
                        color = Gray,
                        shape = RoundedCornerShape(16.dp)
                    ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = AccentBrand,
                    cursorColor = AccentBrand
                ),

                shape = RoundedCornerShape(16.dp),
                textStyle = MaterialTheme.typography.titleMedium,

                placeholder = {
                    Text(
                        "Enter BIN card",
                        style = MaterialTheme.typography.titleSmall,
                    )
                },

                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search icon",
                        tint = Black,
                        modifier = Modifier
                            .size(24.dp)

                    )
                },

                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            when {
                uiState.isLoading -> LoadingIndicator()
                uiState.error != null -> ErrorView(
                    message = uiState.error,
                    onRetry = loadBinInfo
                )

                else -> Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .background(White)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        colors = CardDefaults.cardColors(
                            containerColor = White
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .background(White)
                        ) {
                            Column {
                                Text(
                                    text = "Country",
                                    style = MaterialTheme.typography.labelSmall,
                                )

                                Spacer(modifier = Modifier.height(2.dp))

                                binInfo.countryName?.let {
                                    Text(
                                        text = it,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))
                            Column {
                                Text(
                                    text = "Coordinates",
                                    style = MaterialTheme.typography.labelSmall,
                                )

                                Spacer(modifier = Modifier.height(2.dp))

                                Text(
                                    text = "binInfo.countryLatitude" + "binInfo.countryLongitude",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }

                            Spacer(modifier = Modifier.height(20.dp))
                            Column {
                                Text(
                                    text = "Card Type",
                                    style = MaterialTheme.typography.labelSmall,
                                )

                                Spacer(modifier = Modifier.height(2.dp))

                                binInfo.brand?.let {
                                    Text(
                                        text = it,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))
                            Column {
                                Text(
                                    text = "Bank name",
                                    style = MaterialTheme.typography.labelSmall,
                                )

                                Spacer(modifier = Modifier.height(2.dp))

                                binInfo.bankName?.let {
                                    Text(
                                        text = it,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))
                            Column {
                                Text(
                                    text = "Bank url",
                                    style = MaterialTheme.typography.labelSmall,
                                )

                                Spacer(modifier = Modifier.height(2.dp))

                                binInfo.bankUrl?.let {
                                    Text(
                                        text = it,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))
                            Column {
                                Text(
                                    text = "Bank phone",
                                    style = MaterialTheme.typography.labelSmall,
                                )

                                Spacer(modifier = Modifier.height(2.dp))

                                binInfo.bankPhone?.let {
                                    Text(
                                        text = it,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))
                            Column {
                                Text(
                                    text = "City",
                                    style = MaterialTheme.typography.labelSmall,
                                )

                                Spacer(modifier = Modifier.height(2.dp))

                                binInfo.bankCity?.let {
                                    Text(
                                        text = it,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}