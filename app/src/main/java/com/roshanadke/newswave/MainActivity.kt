package com.roshanadke.newswave

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.roshanadke.common.utils.navigation.Navigation
import com.roshanadke.newswave.ui.theme.NewsWaveTheme

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
                com.roshanadke.common.utils.navigation.Navigation(navController = navController)
            }
        }
    }
}

