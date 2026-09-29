package com.aiansweringmachine.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aiansweringmachine.R
import com.aiansweringmachine.platform.telecom.IncomingCallState

@Composable
fun ActiveCallScreen(
    call: IncomingCallState,
    onAnswer: () -> Unit,
    onReject: () -> Unit,
    onDisconnect: () -> Unit
) {
    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(stringResource(R.string.real_call_detected), style = MaterialTheme.typography.headlineSmall)
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(stringResource(R.string.incoming_call_label), style = MaterialTheme.typography.labelLarge)
                Text(call.number, style = MaterialTheme.typography.headlineMedium)
                Text(stringResource(R.string.choose_call_action), style = MaterialTheme.typography.bodyLarge)
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = onAnswer, modifier = Modifier.weight(1f)) {
                Text(stringResource(R.string.answer_call))
            }
            Button(onClick = onReject, modifier = Modifier.weight(1f)) {
                Text(stringResource(R.string.reject_call))
            }
        }
        Button(onClick = onDisconnect, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.end_call))
        }
    }
}
