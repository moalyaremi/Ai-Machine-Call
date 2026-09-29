package com.aiansweringmachine.platform.telecom

import android.content.Intent
import android.telecom.Call
import android.telecom.InCallService
import com.aiansweringmachine.MainActivity
import com.aiansweringmachine.domain.repository.AssistantPreferencesRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.SupervisorJob

@AndroidEntryPoint
class AiInCallService : InCallService() {
    @Inject lateinit var controller: TelecomCallController
    @Inject lateinit var preferences: AssistantPreferencesRepository
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private var autoAnswerJob: Job? = null

    override fun onCallAdded(call: Call) {
        super.onCallAdded(call)
        controller.attach(call)
        autoAnswerJob?.cancel()
        autoAnswerJob = serviceScope.launch {
            if (preferences.autoAnswerEnabled.first()) {
                delay(preferences.autoAnswerDelaySeconds.first() * 1_000L)
                controller.answerIfRinging()
            }
        }
        startActivity(
            Intent(this, MainActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        )
    }

    override fun onCallRemoved(call: Call) {
        autoAnswerJob?.cancel()
        controller.detach(call)
        super.onCallRemoved(call)
    }

    override fun onDestroy() {
        autoAnswerJob?.cancel()
        serviceScope.coroutineContext[Job]?.cancel()
        super.onDestroy()
    }
}
