package com.aiansweringmachine.domain.repository

import com.aiansweringmachine.domain.model.CallerRule
import kotlinx.coroutines.flow.Flow

interface CallerRuleRepository {
    fun observeEnabledRules(): Flow<List<CallerRule>>
    suspend fun save(rule: CallerRule)
    suspend fun delete(rule: CallerRule)
}
