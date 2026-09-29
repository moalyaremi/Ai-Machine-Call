package com.aiansweringmachine.domain.telecom

data class TelecomCapabilities(
    val callScreeningAvailable: Boolean,
    val callScreeningRoleHeld: Boolean,
    val dialerRoleHeld: Boolean,
    val automaticVoiceAnswerSupported: Boolean
)

interface TelecomCapabilityChecker {
    fun getCapabilities(): TelecomCapabilities
}
