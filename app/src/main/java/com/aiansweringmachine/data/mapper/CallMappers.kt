package com.aiansweringmachine.data.mapper

import com.aiansweringmachine.data.local.CallSummaryEntity
import com.aiansweringmachine.domain.model.CallSummary
import java.time.Instant

fun CallSummaryEntity.toDomain() = CallSummary(
    id = id,
    callerDisplayName = callerDisplayName,
    callerPhoneNumber = callerPhoneNumber,
    createdAt = Instant.ofEpochMilli(createdAtEpochMillis),
    topic = topic,
    priority = priority,
    followUpRequested = followUpRequested,
    summary = summary,
    isFollowUpComplete = isFollowUpComplete
)

fun CallSummary.toEntity() = CallSummaryEntity(
    id = id,
    callerDisplayName = callerDisplayName,
    callerPhoneNumber = callerPhoneNumber,
    createdAtEpochMillis = createdAt.toEpochMilli(),
    topic = topic,
    priority = priority,
    followUpRequested = followUpRequested,
    summary = summary,
    isFollowUpComplete = isFollowUpComplete
)
