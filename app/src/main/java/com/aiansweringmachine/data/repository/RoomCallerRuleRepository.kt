package com.aiansweringmachine.data.repository

import com.aiansweringmachine.data.local.CallerRuleDao
import com.aiansweringmachine.data.mapper.toDomain
import com.aiansweringmachine.data.mapper.toEntity
import com.aiansweringmachine.domain.model.CallerRule
import com.aiansweringmachine.domain.repository.CallerRuleRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomCallerRuleRepository @Inject constructor(
    private val dao: CallerRuleDao
) : CallerRuleRepository {
    override fun observeEnabledRules() = dao.observeEnabled().map { items -> items.map { it.toDomain() } }
    override suspend fun save(rule: CallerRule) = dao.insert(rule.toEntity())
    override suspend fun delete(rule: CallerRule) = dao.delete(rule.toEntity())
}
