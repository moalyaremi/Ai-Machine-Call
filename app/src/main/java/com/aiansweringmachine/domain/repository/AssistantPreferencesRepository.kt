package com.aiansweringmachine.domain.repository

import com.aiansweringmachine.domain.model.AnsweringMode
import kotlinx.coroutines.flow.Flow

interface AssistantPreferencesRepository {
    val answeringMode: Flow<AnsweringMode>
    val assistantEnabled: Flow<Boolean>
    val autoAnswerEnabled: Flow<Boolean>
    val autoAnswerDelaySeconds: Flow<Int>
    suspend fun setAnsweringMode(mode: AnsweringMode)
    suspend fun setAssistantEnabled(enabled: Boolean)
    suspend fun setAutoAnswerEnabled(enabled: Boolean)
    suspend fun setAutoAnswerDelaySeconds(seconds: Int)
}
