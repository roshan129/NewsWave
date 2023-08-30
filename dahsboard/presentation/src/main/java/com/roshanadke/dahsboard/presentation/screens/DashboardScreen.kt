@file:OptIn(ExperimentalMaterial3Api::class)

package com.roshanadke.dahsboard.presentation.screens

import android.app.Activity
import android.content.res.Resources
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.roshanadke.common.utils.KEY_ARTICLE
import com.roshanadke.common.utils.STARTING_PAGE_INDEX
import com.roshanadke.common.utils.isScrolledToEnd
import com.roshanadke.common.utils.navigation.Screen
import com.roshanadke.dahsboard.presentation.screens.components.SearchBar
import com.roshanadke.dahsboard.presentation.viewmodel.NewsDashboardViewModel
import com.roshanadke.dashboard.domain.model.Article
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DashboardScreen(
    navController: NavController,
    viewModel: NewsDashboardViewModel = hiltViewModel(),
) {





    val newsListState = viewModel.newsListState.value

    val selectedChipItem = viewModel.selectedChipItem.value

    val scrollState = rememberLazyListState()


    val endOfListReached by remember {
        derivedStateOf {
            scrollState.isScrolledToEnd()
        }
    }

    LaunchedEffect(endOfListReached) {
        if (endOfListReached) {
            viewModel.incrementPageNumber()
            val incrementedPageNumber = viewModel.getPageNumber().toString()
            viewModel.loadMoreNewsItems(selectedChipItem, incrementedPageNumber)
        }
    }


    Box {

        Column(
            modifier = Modifier.fillMaxSize(),
            /* horizontalAlignment = Alignment.CenterHorizontally,
             verticalArrangement = Arrangement.Center*/
        ) {


            val scrollToTop = viewModel.scrollToTopState.value

            val chipItemList = listOf(
                "Tech", "Science", "Business", "Entertainment",
                "Sports", "Health", "Politics", "Travel", "Fashion", "Food"
            )

            LaunchedEffect(scrollToTop) {
                if (scrollToTop) scrollState.scrollToItem(0)
                viewModel.updateScrollToTop(false)
            }

            LazyRow(
                Modifier.padding(start = 8.dp, end = 8.dp, top = 12.dp),
            ) {


                items(chipItemList) { item ->
                    ChipsItems(
                        chipItem = item,
                        selectedItem = selectedChipItem,
                        onChipItemSelected = { newlySelectedChipItem ->
                            if (newlySelectedChipItem != selectedChipItem) {
                                viewModel.setSelectedChipItem(newlySelectedChipItem)
                                viewModel.setPageNumber(1)
                                viewModel.fetchInitialNewsDashboardList(
                                    newlySelectedChipItem,
                                    STARTING_PAGE_INDEX
                                )
                            }
                        }
                    )
                }

            }


            LazyColumn(state = scrollState) {
                val newsList = newsListState.articles
                itemsIndexed(newsList) { index, article ->

                    NewsItemCard(
                        article,
                        onNewsItemClicked = {
                            navController.currentBackStackEntry?.savedStateHandle?.apply {
                                set(KEY_ARTICLE, article)
                            }
                            //navController.navigate(Screen.DetailsScreen.route)
                            navController.navigate(Screen.ArticleScreen.route)
                        }
                    )
                    if (index == newsList.size - 1 && newsListState.isLoading) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp, top = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(color = Color.Red)


                        }
                    }

                }
                /*item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if(viewModel.getPageNumber() != 1 && newsListState.isLoading) {
                            CircularProgressIndicator(color = Color.Red)
                        }
                    }
                }*/


            }

            if (newsListState.error != null) {
                ShowErrorText(newsListState.error)

            }

        }

        Log.d("TAG", "DashboardScreen: page number: ${viewModel.getPageNumber()}")

        if (newsListState.isLoading && viewModel.getPageNumber() == 1) {
            ShowProgressBar()
        }

    }

}

@Composable
fun ShowErrorText(error: String) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = error, color = Color.Red, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }

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

@Composable
fun ChipsItems(
    chipItem: String,
    selectedItem: String,
    onChipItemSelected: (chipItem: String) -> Unit,
) {

    val chipColor = FilterChipDefaults.filterChipColors(
        containerColor = Color.White,
        labelColor = Color.Black,
        selectedContainerColor = Color.Red,
        selectedLabelColor = Color.White
    )

    FilterChip(
        modifier = Modifier.padding(horizontal = 4.dp),
        selected = (chipItem == selectedItem),
        onClick = {
            onChipItemSelected(chipItem)
        },
        label = {
            Text(text = chipItem)
        },
        colors = chipColor
    )
}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun NewsItemCard(
    article: Article,
    onNewsItemClicked: () -> Unit,
) {

    if (article.url.isEmpty() || article.urlToImage.isEmpty()) {
        return
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                onNewsItemClicked()
            }
    ) {


        Spacer(modifier = Modifier.height(12.dp))
        Image(
            painter = rememberImagePainter(
                data = article.urlToImage,
                builder = {
                    crossfade(true)
                }),
            contentDescription = article.source?.name,
            modifier = Modifier
                .aspectRatio(16f / 9f)
                /*.fillMaxSize()
                .height(250.dp)*/
                .padding(5.dp)
            /*.clip(shape = RoundedCornerShape(24.dp))*/

        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = article.title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = article.source?.name ?: "",
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        Spacer(modifier = Modifier.height(18.dp))
        Divider()


    }


}

