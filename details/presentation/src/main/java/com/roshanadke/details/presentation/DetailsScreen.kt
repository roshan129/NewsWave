package com.roshanadke.details.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.roshanadke.dashboard.domain.model.Article

@Composable
fun DetailsScreen(
    navController: NavController,
) {

    val articleDetails =
        navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        articleDetails?.let { article ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                /*horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center*/
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = article.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = article.description,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = article.content,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                )
            }
        }


    }

}
