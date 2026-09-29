package com.aiansweringmachine.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiansweringmachine.domain.repository.AssistantPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: AssistantPreferencesRepository
) : ViewModel() {
    val state = combine(
        repository.assistantEnabled,
        repository.autoAnswerEnabled,
        repository.autoAnswerDelaySeconds
    ) { assistantEnabled, autoAnswerEnabled, delay ->
        SettingsUiState(assistantEnabled, autoAnswerEnabled, delay)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), SettingsUiState())

    fun setAutoAnswerEnabled(enabled: Boolean) = viewModelScope.launch {
        repository.setAutoAnswerEnabled(enabled)
    }

    fun setDelay(seconds: Int) = viewModelScope.launch {
        repository.setAutoAnswerDelaySeconds(seconds)
    }
}

data class SettingsUiState(
    val assistantEnabled: Boolean = false,
    val autoAnswerEnabled: Boolean = false,
    val autoAnswerDelaySeconds: Int = 10
)
