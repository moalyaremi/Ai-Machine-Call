package com.aiansweringmachine.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiansweringmachine.domain.repository.AssistantPreferencesRepository
import com.aiansweringmachine.domain.model.CallSummary
import com.aiansweringmachine.domain.model.Priority
import com.aiansweringmachine.domain.usecase.SaveCallSummaryUseCase
import java.time.Instant
import kotlinx.coroutines.launch
import com.aiansweringmachine.domain.usecase.ObserveRecentCallsUseCase
import com.aiansweringmachine.domain.telecom.TelecomCapabilityChecker
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import com.aiansweringmachine.platform.telecom.TelecomCallController

@HiltViewModel
class DashboardViewModel @Inject constructor(
    observeRecentCalls: ObserveRecentCallsUseCase,
    preferences: AssistantPreferencesRepository,
    private val saveCallSummary: SaveCallSummaryUseCase,
    capabilityChecker: TelecomCapabilityChecker,
    private val callController: TelecomCallController
) : ViewModel() {
    private val preferencesRepository = preferences
    val capabilities = capabilityChecker.getCapabilities()
    val activeCall = callController.state
    val state = combine(
        observeRecentCalls(),
        preferences.assistantEnabled
    ) { calls, enabled -> DashboardUiState(calls = calls, assistantEnabled = enabled) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), DashboardUiState())

    fun setAssistantEnabled(enabled: Boolean) {
        viewModelScope.launch { preferencesRepository.setAssistantEnabled(enabled) }
    }

    fun addDemoCall() {
        viewModelScope.launch {
            saveCallSummary(
                CallSummary(
                    id = "demo-${System.currentTimeMillis()}",
                    callerDisplayName = "أحمد (تجربة)",
                    callerPhoneNumber = "+967700000000",
                    createdAt = Instant.now(),
                    topic = "متابعة طلب الجهاز",
                    priority = Priority.HIGH,
                    followUpRequested = true,
                    summary = "مكالمة تجريبية: يريد التواصل اليوم."
                )
            )
        }
    }

    fun answerCall() = callController.answer()
    fun rejectCall() = callController.reject()
    fun disconnectCall() = callController.disconnect()
}

data class DashboardUiState(
    val calls: List<com.aiansweringmachine.domain.model.CallSummary> = emptyList(),
    val assistantEnabled: Boolean = false
)
