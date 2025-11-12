package com.example.bankcard.presentation.binsearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankcard.domain.binsearch.BinSearchInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BinSearchViewModel(
    private val interactor: BinSearchInteractor
) : ViewModel() {

    private val _state = MutableStateFlow(BinSearchState())
    val state: StateFlow<BinSearchState> = _state.asStateFlow()

    fun onEvent(event: BinSearchEvent) {
        when (event) {
            is BinSearchEvent.SearchQueryChanged -> {
                _state.update { it.copy(searchQuery = event.query) }
            }
            is BinSearchEvent.SearchBinInfo -> {
                searchBinInfo()
            }
            is BinSearchEvent.ClearError -> {
                _state.update { it.copy(error = null) }
            }

            is BinSearchEvent.ShowBottomSheet -> {
                _state.update { it.copy(showBottomSheet = event.show) }
            }
        }
    }

    private fun searchBinInfo() {
        val bin = _state.value.searchQuery.trim()
        if (bin.length < 6) {
            _state.update { it.copy(error = "BIN must be at least 6 digits") }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            interactor.getBinInfo(bin).fold(
                onSuccess = { binInfo ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            binInfo = binInfo,
                            error = null,
                            showBottomSheet = true
                        )
                    }
                },
                onFailure = { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = error.message ?: "Unknown error occurred"
                        )
                    }
                }
            )
        }
    }
}