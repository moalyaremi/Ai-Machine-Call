package com.aiansweringmachine.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.res.stringResource
import com.aiansweringmachine.R
import com.aiansweringmachine.presentation.screen.DashboardScreen
import com.aiansweringmachine.presentation.screen.CallerRulesScreen
import com.aiansweringmachine.presentation.screen.SmartCallsScreen
import com.aiansweringmachine.presentation.screen.DemoModeScreen
import com.aiansweringmachine.presentation.screen.SettingsScreen

@Composable
fun AppNavHost(
    onRequestCallScreeningRole: () -> Unit,
    onRequestDialerRole: () -> Unit
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppRoute.Dashboard.route) {
        composable(AppRoute.Dashboard.route) {
            DashboardScreen(
                onOpenRules = { navController.navigate(AppRoute.CallerRules.route) },
                onOpenInbox = { navController.navigate(AppRoute.SmartCalls.route) },
                onRequestCallScreeningRole = onRequestCallScreeningRole,
                onOpenDemoMode = { navController.navigate(AppRoute.DemoMode.route) },
                onRequestDialerRole = onRequestDialerRole,
                onOpenSettings = { navController.navigate(AppRoute.Settings.route) }
            )
        }
        composable(AppRoute.SmartCalls.route) { SmartCallsScreen() }
        composable(AppRoute.Settings.route) { SettingsScreen() }
        composable(AppRoute.CallerRules.route) { CallerRulesScreen() }
        composable(AppRoute.DemoMode.route) { DemoModeScreen() }
    }
}
