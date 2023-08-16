package com.roshanadke.details.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.roshanadke.common.utils.navigation.FeatureApi

interface DetailsApi : FeatureApi {

}

class DetailsApiImpl: DetailsApi {
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        InternalDetailsNewsApi.registerGraph(
            navHostController,
            navGraphBuilder
        )
    }
}