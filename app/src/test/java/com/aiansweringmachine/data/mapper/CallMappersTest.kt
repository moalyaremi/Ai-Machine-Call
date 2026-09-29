package com.aiansweringmachine.data.mapper

import com.aiansweringmachine.data.local.CallSummaryEntity
import com.aiansweringmachine.domain.model.Priority
import java.time.Instant
import org.junit.Assert.assertEquals
import org.junit.Test

class CallMappersTest {
    @Test
    fun `call summary round trips through room entity`() {
        val entity = CallSummaryEntity(
            id = "call-1",
            callerDisplayName = "أحمد",
            callerPhoneNumber = "+967700000000",
            createdAtEpochMillis = 1_700_000_000_000,
            topic = "طلب جهاز",
            priority = Priority.HIGH,
            followUpRequested = true,
            summary = "يريد متابعة الطلب اليوم",
            isFollowUpComplete = false
        )

        val restored = entity.toDomain().toEntity()

        assertEquals(entity, restored)
        assertEquals(Instant.ofEpochMilli(entity.createdAtEpochMillis), entity.toDomain().createdAt)
    }
}
