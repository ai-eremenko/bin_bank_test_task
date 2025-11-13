package com.example.bankcard.feature.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankcard.R
import com.example.bankcard.domain.history.HistoryInteractor
import com.example.bankcard.domain.model.BinInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val interactor: HistoryInteractor
) : ViewModel() {

    private val _state = MutableStateFlow(HistoryState())
    val state: StateFlow<HistoryState> = _state.asStateFlow()

    fun onEvent(event: HistoryEvent) {
        when (event) {
            is HistoryEvent.LoadHistory -> loadHistory()
            is HistoryEvent.DeleteItem -> deleteItem(event.bin)
            is HistoryEvent.ShowBottomSheet -> showBottomSheet(event.show, event.binInfo)
        }
    }

    private fun loadHistory() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                val history = interactor.getHistory()
                _state.update {
                    it.copy(
                        history = history,
                        isLoading = false
                    )
                }
            } catch (_: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorResId = R.string.failed_to_load_history
                    )
                }
            }
        }
    }

    private fun deleteItem(bin: String) {
        viewModelScope.launch {
            try {
                interactor.deleteFromHistory(bin)
                loadHistory()
            } catch (_: Exception) {
                _state.update {
                    it.copy(errorResId = R.string.failed_to_delete_item)
                }
            }
        }
    }

    private fun showBottomSheet(show: Boolean, binInfo: BinInfo?) {
        _state.update {
            it.copy(
                showBottomSheet = show,
                selectedBinInfo = binInfo
            )
        }
    }
}