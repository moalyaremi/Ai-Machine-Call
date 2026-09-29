package com.aiansweringmachine.platform.telecom

import android.telecom.Call
import android.telecom.CallScreeningService

/**
 * Official Android screening entry point.
 *
 * This first milestone only acknowledges the call without blocking it. Caller
 * classification and user-controlled rules will be connected after capability
 * and role checks are implemented. It deliberately does not claim to answer
 * calls or inject audio into a cellular call.
 */
class AiCallScreeningService : CallScreeningService() {
    override fun onScreenCall(callDetails: Call.Details) {
        val response = CallResponse.Builder()
            .setDisallowCall(false)
            .setRejectCall(false)
            .setSkipCallLog(false)
            .setSkipNotification(false)
            .build()
        respondToCall(callDetails, response)
    }
}
