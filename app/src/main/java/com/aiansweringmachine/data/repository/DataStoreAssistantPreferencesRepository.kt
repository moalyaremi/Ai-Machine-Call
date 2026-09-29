package com.aiansweringmachine.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.aiansweringmachine.domain.model.AnsweringMode
import com.aiansweringmachine.domain.repository.AssistantPreferencesRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreAssistantPreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AssistantPreferencesRepository {
    private object Keys {
        val enabled = booleanPreferencesKey("assistant_enabled")
        val answeringMode = stringPreferencesKey("answering_mode")
        val autoAnswerEnabled = booleanPreferencesKey("auto_answer_enabled")
        val autoAnswerDelaySeconds = androidx.datastore.preferences.core.intPreferencesKey("auto_answer_delay_seconds")
    }

    override val assistantEnabled = dataStore.data.map { it[Keys.enabled] ?: false }
    override val autoAnswerEnabled = dataStore.data.map { it[Keys.autoAnswerEnabled] ?: false }
    override val autoAnswerDelaySeconds = dataStore.data.map { it[Keys.autoAnswerDelaySeconds] ?: 10 }
    override val answeringMode = dataStore.data.map {
        it[Keys.answeringMode]?.let(AnsweringMode::valueOf) ?: AnsweringMode.MANUAL_AI
    }

    override suspend fun setAssistantEnabled(enabled: Boolean) {
        dataStore.edit { it[Keys.enabled] = enabled }
    }

    override suspend fun setAnsweringMode(mode: AnsweringMode) {
        dataStore.edit { it[Keys.answeringMode] = mode.name }
    }

    override suspend fun setAutoAnswerEnabled(enabled: Boolean) {
        dataStore.edit { it[Keys.autoAnswerEnabled] = enabled }
    }

    override suspend fun setAutoAnswerDelaySeconds(seconds: Int) {
        dataStore.edit { it[Keys.autoAnswerDelaySeconds] = seconds.coerceIn(5, 30) }
    }
}
