package com.aiansweringmachine.presentation.navigation

sealed interface AppRoute {
    val route: String

    data object Dashboard : AppRoute { override val route = "dashboard" }
    data object SmartCalls : AppRoute { override val route = "smart_calls" }
    data object Settings : AppRoute { override val route = "settings" }
    data object CallerRules : AppRoute { override val route = "caller_rules" }
    data object DemoMode : AppRoute { override val route = "demo_mode" }
}
