package com.aiansweringmachine.platform.telecom

import android.net.Uri
import android.telecom.Call
import android.telecom.VideoProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

data class IncomingCallState(
    val number: String,
    val status: Int
)

@Singleton
class TelecomCallController @Inject constructor() {
    private var currentCall: Call? = null
    private val _state = MutableStateFlow<IncomingCallState?>(null)
    val state: StateFlow<IncomingCallState?> = _state.asStateFlow()

    fun attach(call: Call) {
        currentCall = call
        call.registerCallback(callback)
        publish(call)
    }

    fun detach(call: Call) {
        call.unregisterCallback(callback)
        if (currentCall == call) {
            currentCall = null
            _state.value = null
        }
    }

    fun answer() {
        currentCall?.answer(VideoProfile.STATE_AUDIO_ONLY)
    }

    fun answerIfRinging() {
        if (currentCall?.state == Call.STATE_RINGING) answer()
    }

    fun reject() {
        currentCall?.reject(false, null)
    }

    fun disconnect() {
        currentCall?.disconnect()
    }

    private fun publish(call: Call) {
        val number = call.details.handle?.let(Uri::toString).orEmpty().ifBlank { "Unknown" }
        _state.value = IncomingCallState(number = number, status = call.state)
    }

    private val callback = object : Call.Callback() {
        override fun onStateChanged(call: Call, state: Int) = publish(call)
    }
}
