package com.example.bankcard.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankcard.domain.history.HistoryInteractor
import com.example.bankcard.domain.model.BinInfo
import com.example.bankcard.presentation.binsearch.BinSearchEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val interactor: HistoryInteractor
) : ViewModel() {

    private val _state = MutableStateFlow(HistoryState())
    val state: StateFlow<HistoryState> = _state.asStateFlow()

    init {
        loadHistory()
    }

    fun onEvent(event: HistoryEvent) {
        when (event) {
            is HistoryEvent.LoadHistory -> loadHistory()
            is HistoryEvent.DeleteItem -> deleteItem(event.bin)
            is HistoryEvent.ClearError -> clearError()
            is HistoryEvent.ShowBottomSheet -> showBottomSheet(event.show, event.binInfo)

        }
    }

    private fun loadHistory() {
        _state.update { it.copy(isLoading = true, error = null) }

        interactor.getHistory()
            .onEach { history ->
                _state.update {
                    it.copy(
                        history = history,
                        isLoading = false,
                        error = null
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun deleteItem(bin: String) {
        viewModelScope.launch {
            try {
                interactor.deleteFromHistory(bin)
            } catch (e: Exception) {
                _state.update {
                    it.copy(error = "Failed to delete item: ${e.message}")
                }
            }
        }
    }

    private fun clearError() {
        _state.update { it.copy(error = null) }
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