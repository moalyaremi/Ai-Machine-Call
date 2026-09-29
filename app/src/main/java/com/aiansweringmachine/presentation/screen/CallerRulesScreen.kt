package com.aiansweringmachine.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aiansweringmachine.R
import com.aiansweringmachine.presentation.viewmodel.CallerRulesViewModel

@Composable
fun CallerRulesScreen(viewModel: CallerRulesViewModel = hiltViewModel()) {
    val rules by viewModel.rules.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(stringResource(R.string.caller_rules), style = MaterialTheme.typography.headlineSmall)
        Button(onClick = viewModel::addDemoRules) {
            Text(stringResource(R.string.add_demo_rules))
        }
        if (rules.isEmpty()) {
            Text(stringResource(R.string.no_caller_rules))
        } else {
            rules.forEach { rule ->
                Button(onClick = { viewModel.delete(rule) }) {
                    Text(stringResource(R.string.delete_rule, rule.name))
                }
                Text(stringResource(R.string.caller_rule_item, rule.name, rule.matchValue))
            }
        }
    }
}
