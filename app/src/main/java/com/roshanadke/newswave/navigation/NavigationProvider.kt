package com.roshanadke.newswave.navigation

import com.roshanadke.dahsboard.presentation.navigation.DashboardApi
import com.roshanadke.details.presentation.navigation.DetailsApi

data class NavigationProvider(
    val dashboardApi: DashboardApi,
    val detailsApi: DetailsApi
)

