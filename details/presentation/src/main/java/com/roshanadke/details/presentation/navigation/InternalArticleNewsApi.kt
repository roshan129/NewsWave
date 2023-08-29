package com.roshanadke.details.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.roshanadke.common.utils.ARTICLE_GRAPH_ROUTE
import com.roshanadke.common.utils.navigation.FeatureApi
import com.roshanadke.common.utils.navigation.Screen
import com.roshanadke.details.presentation.DetailsScreen
import com.roshanadke.common.utils.DETAILS_GRAPH_ROUTE
import com.roshanadke.details.presentation.screen.ArticleScreen

object InternalArticleNewsApi : FeatureApi {
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {

        navGraphBuilder.navigation(
            startDestination = Screen.ArticleScreen.route,
            route = ARTICLE_GRAPH_ROUTE
        ) {
            composable(
                route = Screen.ArticleScreen.route,
            ) {
                ArticleScreen(navHostController)
            }
        }

    }
}