package com.example.bankcard.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankcard.domain.history.HistoryInteractor
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
                // History will be updated automatically through Flow
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
}