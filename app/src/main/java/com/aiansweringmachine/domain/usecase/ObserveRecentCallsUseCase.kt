package com.aiansweringmachine.domain.usecase

import com.aiansweringmachine.domain.repository.CallHistoryRepository
import javax.inject.Inject

class ObserveRecentCallsUseCase @Inject constructor(
    private val repository: CallHistoryRepository
) {
    operator fun invoke() = repository.observeRecentCalls()
}
