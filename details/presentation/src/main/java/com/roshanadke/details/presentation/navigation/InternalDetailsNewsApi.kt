package com.roshanadke.details.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.roshanadke.common.utils.navigation.FeatureApi
import com.roshanadke.common.utils.navigation.Screen
import com.roshanadke.details.presentation.DetailsScreen
import com.roshanadke.newswave.DETAILS_GRAPH_ROUTE

object InternalDetailsNewsApi: FeatureApi {
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = Screen.DetailsScreen.route,
            route = DETAILS_GRAPH_ROUTE
        ) {
            composable(Screen.DetailsScreen.route) {
                DetailsScreen()
            }
        }
    }


}