package com.aiansweringmachine.domain.repository

import com.aiansweringmachine.domain.model.CallSummary
import kotlinx.coroutines.flow.Flow

interface CallHistoryRepository {
    fun observeRecentCalls(): Flow<List<CallSummary>>
    suspend fun save(summary: CallSummary)
    suspend fun deleteAll()
}
