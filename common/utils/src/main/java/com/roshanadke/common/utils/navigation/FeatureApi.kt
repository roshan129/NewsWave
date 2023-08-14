package com.roshanadke.common.utils.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface FeatureApi {

    fun registerGraph(navHostController: NavHostController, navGraphBuilder: NavGraphBuilder)

}