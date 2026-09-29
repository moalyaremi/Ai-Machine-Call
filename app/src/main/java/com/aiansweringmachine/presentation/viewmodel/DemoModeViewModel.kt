package com.aiansweringmachine.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiansweringmachine.domain.model.CallSummary
import com.aiansweringmachine.domain.model.DemoCallScenario
import com.aiansweringmachine.domain.usecase.SaveCallSummaryUseCase
import com.aiansweringmachine.domain.speech.TextToSpeechProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.Instant
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DemoModeViewModel @Inject constructor(
    private val saveCallSummary: SaveCallSummaryUseCase,
    private val textToSpeech: TextToSpeechProvider
) : ViewModel() {
    private val _state = MutableStateFlow(DemoModeUiState())
    val state: StateFlow<DemoModeUiState> = _state.asStateFlow()

    fun select(scenario: DemoCallScenario) {
        _state.value = _state.value.copy(selectedScenario = scenario, completedSummary = null)
    }

    fun runSimulation() {
        val scenario = _state.value.selectedScenario ?: return
        viewModelScope.launch {
            _state.value = _state.value.copy(phase = DemoPhase.LISTENING)
            textToSpeech.speak("مرحبًا، أنا المساعد الذكي. كيف يمكنني مساعدتك؟")
            kotlinx.coroutines.delay(1_500)
            _state.value = _state.value.copy(phase = DemoPhase.THINKING)
            kotlinx.coroutines.delay(1_200)
            _state.value = _state.value.copy(phase = DemoPhase.SPEAKING)
            textToSpeech.speak(scenario.summary)
            kotlinx.coroutines.delay(1_500)
            val summary = CallSummary(
                id = "simulation-${System.currentTimeMillis()}",
                callerDisplayName = scenario.callerName,
                callerPhoneNumber = scenario.phoneNumber,
                createdAt = Instant.now(),
                topic = scenario.topic,
                priority = scenario.priority,
                followUpRequested = scenario.followUpRequested,
                summary = scenario.summary
            )
            saveCallSummary(summary)
            _state.value = _state.value.copy(phase = DemoPhase.COMPLETED, completedSummary = summary)
        }
    }

    fun stopSimulation() {
        textToSpeech.stop()
        _state.value = _state.value.copy(phase = DemoPhase.IDLE)
    }
}

data class DemoModeUiState(
    val selectedScenario: DemoCallScenario? = DemoCallScenario.KNOWN,
    val completedSummary: CallSummary? = null,
    val phase: DemoPhase = DemoPhase.IDLE
)

enum class DemoPhase { IDLE, LISTENING, THINKING, SPEAKING, COMPLETED }
