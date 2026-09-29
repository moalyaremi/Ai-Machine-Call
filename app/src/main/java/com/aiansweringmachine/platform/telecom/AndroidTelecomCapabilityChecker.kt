package com.aiansweringmachine.platform.telecom

import android.content.Context
import android.os.Build
import android.telecom.TelecomManager
import com.aiansweringmachine.domain.telecom.TelecomCapabilities
import com.aiansweringmachine.domain.telecom.TelecomCapabilityChecker
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AndroidTelecomCapabilityChecker @Inject constructor(
    @ApplicationContext private val context: Context
) : TelecomCapabilityChecker {
    override fun getCapabilities(): TelecomCapabilities {
        val telecomAvailable = context.getSystemService(TelecomManager::class.java) != null
        val roleHeld = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            context.getSystemService(android.app.role.RoleManager::class.java)
                ?.isRoleHeld(android.app.role.RoleManager.ROLE_CALL_SCREENING) == true
        } else {
            false
        }
        val dialerRoleHeld = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            context.getSystemService(android.app.role.RoleManager::class.java)
                ?.isRoleHeld(android.app.role.RoleManager.ROLE_DIALER) == true
        } else {
            false
        }
        // Android does not guarantee TTS injection into a normal cellular call.
        return TelecomCapabilities(
            callScreeningAvailable = telecomAvailable,
            callScreeningRoleHeld = roleHeld,
            dialerRoleHeld = dialerRoleHeld,
            automaticVoiceAnswerSupported = false
        )
    }
}
