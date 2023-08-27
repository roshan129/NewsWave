package com.roshanadke.details.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.roshanadke.dashboard.domain.model.Article


@OptIn(ExperimentalCoilApi::class)
@Composable
fun DetailsScreen(
    navController: NavController,
) {

    val articleDetails =
        navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {

        articleDetails?.let { article ->

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Image(
                        painter = rememberImagePainter(data = article.urlToImage),
                        contentDescription = article.source?.name,
                        modifier = Modifier
                            .aspectRatio(16f / 9f)
                            .padding(5.dp)
                    )
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
                        text = getArticleFormattedContent(article.content),
                        fontWeight = FontWeight.Normal,
                        fontSize = 22.sp,
                    )
                }
            }
        }


    }

}

fun getArticleFormattedContent(content: String): String {
    /*Log.d("TAG", "getArticleFormattedContent: $content")
    val indexOfThreeDots = content.indexOf("…")
    var formattedContent = ""
    formattedContent = if (indexOfThreeDots != -1) {
        content.substring(0, indexOfThreeDots + 3) + "."
    } else {
        content
    }
    return formattedContent + formattedContent*/
    val regex = Regex("... \\[\\+\\d+ chars\\]")
    val formattedContent = content.replace(regex, ".")
    return formattedContent.repeat(5)
}



