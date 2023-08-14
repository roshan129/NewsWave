package com.roshanadke.dahsboard.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.roshanadke.common.utils.navigation.FeatureApi

interface DashboardApi : FeatureApi {

}

class DashboardApiImplementation : DashboardApi {
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        InternalDashboardNewsApi.registerGraph(navHostController, navGraphBuilder)
    }

}