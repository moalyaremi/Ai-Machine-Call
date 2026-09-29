package com.aiansweringmachine.domain.model

enum class CallerCategory { KNOWN, UNKNOWN, POTENTIAL_SPAM, BUSINESS, VIP }

data class CallerRule(
    val id: Long = 0,
    val name: String,
    val matchValue: String,
    val category: CallerCategory,
    val answerMode: AnsweringMode,
    val isEnabled: Boolean = true
)
