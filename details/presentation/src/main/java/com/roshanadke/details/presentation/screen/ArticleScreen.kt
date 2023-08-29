package com.roshanadke.details.presentation.screen

import android.content.Context
import android.content.Intent
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import com.roshanadke.common.utils.KEY_ARTICLE
import com.roshanadke.dashboard.domain.model.Article

@Composable
fun ArticleScreen(
    navController: NavHostController
) {

    val article =
        navController.previousBackStackEntry?.savedStateHandle?.get<Article>(KEY_ARTICLE)

    val context = LocalContext.current

    val isLoading = remember { mutableStateOf(true) }


    Column(
        Modifier.fillMaxSize(),

        ) {
        var anchorPosition by remember { mutableStateOf<Offset?>(null) }
        var expanded by remember { mutableStateOf(false) }

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,

            ) {

            Image(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .padding(12.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )

            Box(
                Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.TopEnd)
            ) {

                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More"
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },


                    ) {
                    DropdownMenuItem(
                        text = {
                            Text(text = "Share")
                        },
                        onClick = {
                            article?.url?.let {
                                shareArticle(it, context)
                            }
                        },
                    )

                }
            }

        }

        article?.url?.let { url ->
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { context ->
                    WebView(context).apply {
                        //settings.javaScriptEnabled = true
                        webViewClient = object : WebViewClient() {
                            override fun onPageFinished(view: WebView?, url: String?) {
                                isLoading.value = false
                                super.onPageFinished(view, url)
                            }
                        }
                        loadUrl(url)
                    }
                }
            )
        } ?: Text(text = "Some error occurred in fetching article", Modifier.padding(top = 24.dp))

    }

    if (isLoading.value) {
        ShowProgressBar()
    }

}

fun shareArticle(url: String, context: Context) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_TEXT, url)
    startActivity(context, Intent.createChooser(intent, "Share article via"), null)

}

@Composable
fun ShowProgressBar() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(color = Color.Red)
    }

}
