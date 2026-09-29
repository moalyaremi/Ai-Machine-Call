package com.aiansweringmachine.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aiansweringmachine.domain.model.Priority

@Entity(tableName = "call_summaries")
data class CallSummaryEntity(
    @PrimaryKey val id: String,
    val callerDisplayName: String?,
    val callerPhoneNumber: String,
    val createdAtEpochMillis: Long,
    val topic: String?,
    val priority: Priority,
    val followUpRequested: Boolean,
    val summary: String?,
    val isFollowUpComplete: Boolean
)
