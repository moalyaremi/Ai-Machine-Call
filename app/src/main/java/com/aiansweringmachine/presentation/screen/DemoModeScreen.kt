package com.aiansweringmachine.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.aiansweringmachine.domain.model.DemoCallScenario
import com.aiansweringmachine.presentation.viewmodel.DemoModeViewModel
import com.aiansweringmachine.presentation.viewmodel.DemoPhase

@Composable
fun DemoModeScreen(viewModel: DemoModeViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(stringResource(R.string.demo_center), style = MaterialTheme.typography.headlineSmall)
        Text(stringResource(R.string.demo_center_description))

        DemoCallScenario.entries.forEach { scenario ->
            Button(onClick = { viewModel.select(scenario) }) {
                Text(scenario.displayName)
            }
        }

        state.selectedScenario?.let { scenario ->
            Card {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(stringResource(R.string.selected_scenario, scenario.displayName))
                    Text(stringResource(R.string.demo_caller, scenario.callerName ?: scenario.phoneNumber))
                    Text(stringResource(R.string.demo_topic, scenario.topic))
                    Text(stringResource(R.string.demo_state_listening))
                    Text(stringResource(R.string.demo_state_thinking))
                    Text(stringResource(R.string.demo_state_speaking))
                    Text(
                        text = when (state.phase) {
                            DemoPhase.LISTENING -> stringResource(R.string.demo_current_listening)
                            DemoPhase.THINKING -> stringResource(R.string.demo_current_thinking)
                            DemoPhase.SPEAKING -> stringResource(R.string.demo_current_speaking)
                            DemoPhase.COMPLETED -> stringResource(R.string.demo_current_completed)
                            DemoPhase.IDLE -> stringResource(R.string.demo_current_idle)
                        },
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }

        Button(onClick = viewModel::runSimulation) {
            Text(stringResource(R.string.run_demo_call))
        }
        Button(onClick = viewModel::stopSimulation) {
            Text(stringResource(R.string.stop_demo_call))
        }

        state.completedSummary?.let { summary ->
            Card {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(stringResource(R.string.demo_completed), style = MaterialTheme.typography.titleMedium)
                    Text(summary.summary.orEmpty())
                    Text(stringResource(R.string.demo_saved_to_inbox))
                }
            }
        }
    }
}
