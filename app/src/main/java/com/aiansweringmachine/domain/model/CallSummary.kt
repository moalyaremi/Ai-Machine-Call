package com.aiansweringmachine.domain.model

import java.time.Instant

data class CallSummary(
    val id: String,
    val callerDisplayName: String?,
    val callerPhoneNumber: String,
    val createdAt: Instant,
    val topic: String?,
    val priority: Priority,
    val followUpRequested: Boolean,
    val summary: String?,
    val isFollowUpComplete: Boolean = false
)

enum class Priority { LOW, NORMAL, HIGH, URGENT }
