package com.aiansweringmachine.data.mapper

import com.aiansweringmachine.data.local.CallerRuleEntity
import com.aiansweringmachine.domain.model.CallerRule

fun CallerRuleEntity.toDomain() = CallerRule(
    id = id,
    name = name,
    matchValue = matchValue,
    category = category,
    answerMode = answerMode,
    isEnabled = isEnabled
)

fun CallerRule.toEntity() = CallerRuleEntity(
    id = id,
    name = name,
    matchValue = matchValue,
    category = category,
    answerMode = answerMode,
    isEnabled = isEnabled
)
