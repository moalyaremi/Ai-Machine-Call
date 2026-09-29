package com.aiansweringmachine.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aiansweringmachine.domain.model.AnsweringMode
import com.aiansweringmachine.domain.model.CallerCategory

@Entity(tableName = "caller_rules")
data class CallerRuleEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val matchValue: String,
    val category: CallerCategory,
    val answerMode: AnsweringMode,
    val isEnabled: Boolean
)
