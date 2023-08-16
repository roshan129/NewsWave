package com.roshanadke.dahsboard.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun DashboardScreen(
    navController: NavController
) {

    Box {
        Column(
            modifier = Modifier.fillMaxSize(),
            /*horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center*/
        ) {
            Text(text = "Dashboard Screen")



        }
    }

}

