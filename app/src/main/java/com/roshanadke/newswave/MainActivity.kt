package com.roshanadke.newswave

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.roshanadke.dahsboard.presentation.navigation.DashboardApi
import com.roshanadke.dahsboard.presentation.navigation.DashboardApiImpl
import com.roshanadke.details.presentation.navigation.DetailsApi
import com.roshanadke.details.presentation.navigation.DetailsApiImpl
import com.roshanadke.newswave.navigation.Navigation
import com.roshanadke.newswave.navigation.NavigationProvider
import com.roshanadke.newswave.ui.theme.NewsWaveTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsWaveTheme {
                /*Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Text(text = "hello world")

                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {

                    }
                }*/
                val navController = rememberNavController()

                val dashboardApi : DashboardApi = DashboardApiImpl()
                val detailsApi : DetailsApi = DetailsApiImpl()
                val navigationProvider =
                    NavigationProvider(
                        dashboardApi, detailsApi
                    )

                Navigation(navHostController = navController,
                    navigationProvider = navigationProvider)
            }
        }
    }
}

