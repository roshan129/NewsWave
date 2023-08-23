package com.roshanadke.newswave.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.roshanadke.newswave.DASHBOARD_GRAPH_ROUTE

@Composable
fun Navigation(
    navHostController: NavHostController,
    navigationProvider: NavigationProvider
) {
    NavHost(navController = navHostController, startDestination = DASHBOARD_GRAPH_ROUTE) {
        navigationProvider.dashboardApi.registerGraph(
            navHostController, this
        )
        navigationProvider.detailsApi.registerGraph(
            navHostController, this
        )
    }
}







