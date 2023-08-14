/*
package com.roshanadke.common.utils.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.roshanadke.dahsboard.presentation.DashboardScreen
import com.roshanadke.details.presentation.DetailsScreen
import com.roshanadke.newswave.DASHBOARD_GRAPH_ROUTE
import com.roshanadke.newswave.DETAILS_GRAPH_ROUTE
import com.roshanadke.newswave.ROOT_GRAPH_ROUTE


@Composable
fun Navigation(
    navController: NavHostController
) {

    NavHost(navController = navController,
        startDestination = DASHBOARD_GRAPH_ROUTE,
        route = ROOT_GRAPH_ROUTE
    )  {


        navigation(
            startDestination = Screen.DashboardScreen.route,
            route = DASHBOARD_GRAPH_ROUTE
        ) {
            composable(Screen.DashboardScreen.route) {
                DashboardScreen(navController = navController)
            }
        }


        navigation(
            startDestination = Screen.DetailsScreen.route,
            route = DETAILS_GRAPH_ROUTE
        ) {
            composable(Screen.DetailsScreen.route) {
                DetailsScreen()
            }
        }


    }

}*/
