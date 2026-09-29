package com.aiansweringmachine.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.aiansweringmachine.presentation.viewmodel.CallInboxViewModel

@Composable
fun SmartCallsScreen(viewModel: CallInboxViewModel = hiltViewModel()) {
    val calls by viewModel.calls.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(stringResource(R.string.smart_calls), style = MaterialTheme.typography.headlineSmall)
        if (calls.isEmpty()) {
            Text(stringResource(R.string.no_calls))
        } else {
            calls.forEach { call ->
                Card {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(call.callerDisplayName ?: call.callerPhoneNumber)
                        call.topic?.let { Text(it, style = MaterialTheme.typography.titleMedium) }
                        call.summary?.let { Text(it) }
                    }
                }
            }
        }
    }
}
