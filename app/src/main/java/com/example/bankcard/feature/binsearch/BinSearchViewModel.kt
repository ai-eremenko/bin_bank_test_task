package com.example.bankcard.feature.binsearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankcard.R
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
            is BinSearchEvent.SearchButtonClicked -> {
                searchBinInfo()
            }
            BinSearchEvent.CloseBottomSheet -> {
                _state.update { it.copy(showBottomSheet = false) }
            }
        }
    }

    private fun searchBinInfo() {
        if (_state.value.isLoading) return
        val bin = _state.value.searchQuery.trim()
        if (bin.length < 6) {
            _state.update { it.copy(errorResId = R.string.bin_min_length_error)  }
            return
        }
        if (bin.length > 8) {
            _state.update { it.copy(errorResId = R.string.bin_max_length_error) }
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
                            errorResId = R.string.unknown_error_occurred
                        )
                    }
                }
            )
        }
    }

}