package com.aiansweringmachine.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiansweringmachine.domain.model.CallerRule
import com.aiansweringmachine.domain.model.AnsweringMode
import com.aiansweringmachine.domain.model.CallerCategory
import com.aiansweringmachine.domain.repository.CallerRuleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class CallerRulesViewModel @Inject constructor(
    private val repository: CallerRuleRepository
) : ViewModel() {
    val rules = repository.observeEnabledRules()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun addDemoRules() {
        viewModelScope.launch {
            repository.save(
                CallerRule(
                    name = "العائلة",
                    matchValue = "family",
                    category = CallerCategory.KNOWN,
                    answerMode = AnsweringMode.MANUAL_AI
                )
            )
            repository.save(
                CallerRule(
                    name = "الأرقام غير المعروفة",
                    matchValue = "unknown",
                    category = CallerCategory.UNKNOWN,
                    answerMode = AnsweringMode.MANUAL_AI
                )
            )
        }
    }

    fun delete(rule: CallerRule) {
        viewModelScope.launch { repository.delete(rule) }
    }
}
