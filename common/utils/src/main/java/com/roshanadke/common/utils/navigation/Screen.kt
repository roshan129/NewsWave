package com.roshanadke.common.utils.navigation


sealed class Screen(val route: String) {

    object DashboardScreen: Screen("dashboard")
    object DetailsScreen: Screen("details")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {arg ->
                append("/$arg")
            }
        }
    }
}