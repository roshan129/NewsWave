package com.roshanadke.dahsboard.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.roshanadke.common.utils.navigation.Screen

@Composable
fun DashboardScreen(
    navController: NavController
) {

    Box {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Dashboard Screen")
            Spacer(modifier = Modifier.height(40.dp))
            Button(onClick = {
                navController.navigate(Screen.DetailsScreen.route)
            }) {
                Text(text = "Details Screen")
            }
        }
    }

}

