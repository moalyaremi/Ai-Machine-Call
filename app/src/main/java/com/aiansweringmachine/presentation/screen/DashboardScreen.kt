package com.aiansweringmachine.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aiansweringmachine.R
import com.aiansweringmachine.presentation.viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    onOpenRules: () -> Unit,
    onOpenInbox: () -> Unit,
    onRequestCallScreeningRole: () -> Unit,
    onOpenDemoMode: () -> Unit,
    onRequestDialerRole: () -> Unit,
    onOpenSettings: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val activeCall by viewModel.activeCall.collectAsStateWithLifecycle()
    if (activeCall != null) {
        ActiveCallScreen(
            call = activeCall!!,
            onAnswer = viewModel::answerCall,
            onReject = viewModel::rejectCall,
            onDisconnect = viewModel::disconnectCall
        )
        return
    }
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = stringResource(R.string.app_name), style = MaterialTheme.typography.headlineMedium)
        Text(text = stringResource(R.string.assistant_subtitle), style = MaterialTheme.typography.titleMedium)
        Button(onClick = onOpenSettings) {
            Text(text = stringResource(R.string.settings))
        }
        Card {
            Column(modifier = Modifier.padding(PaddingValues(20.dp))) {
                Text(text = stringResource(R.string.assistant_title), style = MaterialTheme.typography.titleLarge)
                Text(
                    text = if (state.assistantEnabled) {
                        stringResource(R.string.assistant_enabled)
                    } else {
                        stringResource(R.string.assistant_disabled)
                    },
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Text(text = stringResource(R.string.total_calls, state.calls.size))
        Text(
            text = if (viewModel.capabilities.callScreeningRoleHeld) {
                stringResource(R.string.screening_role_active)
            } else {
                stringResource(R.string.screening_role_inactive)
            },
            style = MaterialTheme.typography.bodyMedium
        )
        Button(onClick = onRequestCallScreeningRole) {
            Text(text = stringResource(R.string.enable_call_screening))
        }
        Button(onClick = onRequestDialerRole) {
            Text(text = stringResource(R.string.enable_default_dialer))
        }
        Text(
            text = if (viewModel.capabilities.dialerRoleHeld) {
                stringResource(R.string.default_dialer_active)
            } else {
                stringResource(R.string.default_dialer_inactive)
            },
            style = MaterialTheme.typography.bodyMedium
        )
        Button(onClick = { viewModel.setAssistantEnabled(!state.assistantEnabled) }) {
            Text(text = stringResource(R.string.toggle_assistant))
        }
        Button(onClick = viewModel::addDemoCall) {
            Text(text = stringResource(R.string.add_demo_call))
        }
        Button(onClick = onOpenInbox) {
            Text(text = stringResource(R.string.smart_calls))
        }
        Button(onClick = onOpenDemoMode) {
            Text(text = stringResource(R.string.demo_center))
        }
        Button(onClick = onOpenRules) {
            Text(text = stringResource(R.string.caller_rules))
        }
    }
}
