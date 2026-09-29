package com.aiansweringmachine.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

class AnsweringModeTest {
    @Test
    fun `all answering modes have stable persistence names`() {
        AnsweringMode.entries.forEach { mode ->
            assertEquals(mode, AnsweringMode.valueOf(mode.name))
        }
    }
}
