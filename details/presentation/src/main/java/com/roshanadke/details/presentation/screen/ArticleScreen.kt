package com.roshanadke.details.presentation.screen

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebViewClientCompat
import com.roshanadke.common.utils.KEY_ARTICLE
import com.roshanadke.dashboard.domain.model.Article

@Composable
fun ArticleScreen(
    navController: NavHostController
) {

    val article =
        navController.previousBackStackEntry?.savedStateHandle?.get<Article>(KEY_ARTICLE)


    article?.url?.let {url ->
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                WebView(context).apply {
                    //settings.javaScriptEnabled = true
                    webViewClient = WebViewClient()
                    loadUrl(url)
                }
            }
        )
    } ?: Text(text = "Some error occurred in fetching article")


}