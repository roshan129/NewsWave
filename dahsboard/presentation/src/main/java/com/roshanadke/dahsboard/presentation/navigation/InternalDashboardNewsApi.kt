package com.roshanadke.dahsboard.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.roshanadke.common.utils.navigation.FeatureApi
import com.roshanadke.common.utils.navigation.Screen
import com.roshanadke.dahsboard.presentation.screens.DashboardScreen
import com.roshanadke.newswave.DASHBOARD_GRAPH_ROUTE

object InternalDashboardNewsApi: FeatureApi {
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = Screen.DashboardScreen.route,
            route = DASHBOARD_GRAPH_ROUTE
        ) {
            composable(Screen.DashboardScreen.route) {
                DashboardScreen(navController = navHostController)
            }
        }
    }
}