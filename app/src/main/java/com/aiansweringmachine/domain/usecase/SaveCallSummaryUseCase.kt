package com.aiansweringmachine.domain.usecase

import com.aiansweringmachine.domain.model.CallSummary
import com.aiansweringmachine.domain.repository.CallHistoryRepository
import javax.inject.Inject

class SaveCallSummaryUseCase @Inject constructor(
    private val repository: CallHistoryRepository
) {
    suspend operator fun invoke(summary: CallSummary) = repository.save(summary)
}
