package com.aiansweringmachine.data.repository

import com.aiansweringmachine.data.local.CallDao
import com.aiansweringmachine.data.mapper.toDomain
import com.aiansweringmachine.data.mapper.toEntity
import com.aiansweringmachine.domain.model.CallSummary
import com.aiansweringmachine.domain.repository.CallHistoryRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomCallHistoryRepository @Inject constructor(
    private val dao: CallDao
) : CallHistoryRepository {
    override fun observeRecentCalls() = dao.observeAll().map { items -> items.map { it.toDomain() } }
    override suspend fun save(summary: CallSummary) = dao.insert(summary.toEntity())
    override suspend fun deleteAll() = dao.deleteAll()
}
