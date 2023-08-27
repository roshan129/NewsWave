package com.roshanadke.details.presentation.navigation

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.roshanadke.common.utils.navigation.FeatureApi
import com.roshanadke.common.utils.navigation.Screen
import com.roshanadke.dashboard.domain.model.Article
import com.roshanadke.details.presentation.DetailsScreen
import com.roshanadke.common.utils.DETAILS_GRAPH_ROUTE
import com.roshanadke.common.utils.KEY_ARTICLE
import com.roshanadke.common.utils.stringToObject

object InternalDetailsNewsApi : FeatureApi {
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = Screen.DetailsScreen.route,
            route = DETAILS_GRAPH_ROUTE
        ) {
            composable(
                route = Screen.DetailsScreen.route,
            ) {
                DetailsScreen(navHostController)
            }
        }
    }
}