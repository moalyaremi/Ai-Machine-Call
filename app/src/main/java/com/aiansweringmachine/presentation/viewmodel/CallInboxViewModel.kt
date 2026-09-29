package com.aiansweringmachine.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiansweringmachine.domain.usecase.ObserveRecentCallsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class CallInboxViewModel @Inject constructor(
    observeRecentCalls: ObserveRecentCallsUseCase
) : ViewModel() {
    val calls = observeRecentCalls()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
}
