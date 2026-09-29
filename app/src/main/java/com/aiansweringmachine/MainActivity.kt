package com.aiansweringmachine

import android.os.Bundle
import android.app.role.RoleManager
import android.content.Intent
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.aiansweringmachine.presentation.navigation.AppNavHost
import com.aiansweringmachine.presentation.theme.AiAnsweringMachineTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AiAnsweringMachineTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavHost(
                        onRequestCallScreeningRole = {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                val roleManager = getSystemService(RoleManager::class.java)
                                if (roleManager.isRoleAvailable(RoleManager.ROLE_CALL_SCREENING)) {
                                    startActivityForResult(
                                        roleManager.createRequestRoleIntent(RoleManager.ROLE_CALL_SCREENING),
                                        REQUEST_CALL_SCREENING_ROLE
                                    )
                                }
                            }
                        },
                        onRequestDialerRole = {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                val roleManager = getSystemService(RoleManager::class.java)
                                if (roleManager.isRoleAvailable(RoleManager.ROLE_DIALER)) {
                                    startActivityForResult(
                                        roleManager.createRequestRoleIntent(RoleManager.ROLE_DIALER),
                                        REQUEST_DIALER_ROLE
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    }

    @Deprecated("RoleManager request result uses the legacy Activity result callback for API 29 compatibility.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CALL_SCREENING_ROLE || requestCode == REQUEST_DIALER_ROLE) {
            // Recreate the Activity so Hilt/ViewModels re-read the current system role state.
            recreate()
        }
    }

    private companion object {
        const val REQUEST_CALL_SCREENING_ROLE = 3001
        const val REQUEST_DIALER_ROLE = 3002
    }
}
