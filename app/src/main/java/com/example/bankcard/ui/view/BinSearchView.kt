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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.bankcard.presentation.binsearch.BinSearchState
import com.example.bankcard.ui.components.BinInfoBottomSheet
import com.example.bankcard.ui.components.BinInfoCard
import com.example.bankcard.ui.components.LoadingIndicator
import com.example.bankcard.ui.theme.AccentBrand
import com.example.bankcard.ui.theme.Black
import com.example.bankcard.ui.theme.Gray
import com.example.bankcard.ui.theme.GrayDark
import com.example.bankcard.ui.theme.White
import com.example.bankcard.ui.view.ErrorView
import com.example.bankcard.utils.BankCardNumberTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BinSearchView(
    uiState: BinSearchState,
    onSearchQueryChanged: (String) -> Unit,
    onSearch: () -> Unit,
    onNavigateToHistory: () -> Unit,
    onShowBottomSheet: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    BinInfoBottomSheet(
        binInfo = uiState.binInfo,
        onDismiss = { onShowBottomSheet(false) }
    )

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
                    ),
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
                value = uiState.searchQuery,
                onValueChange = { newText ->
                    val filteredText = newText.filter { it.isDigit() }
                    onSearchQueryChanged(filteredText)
                },
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
                    focusedBorderColor = GrayDark,
                    cursorColor = GrayDark
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

                singleLine = true,
                visualTransformation = BankCardNumberTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onSearch,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(46.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GrayDark
                )
            ) {
                Text(
                    "Search",
                    style = MaterialTheme.typography.headlineMedium,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onNavigateToHistory,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(46.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Gray
                )
            ) {
                Text(
                    "History",
                    style = MaterialTheme.typography.headlineMedium,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingIndicator()
                    }
                }

                uiState.error != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        ErrorView(
                            message = uiState.error,
                            onRetry = onSearch
                        )
                    }
                }

                else -> {
                }
            }
        }
    }
}