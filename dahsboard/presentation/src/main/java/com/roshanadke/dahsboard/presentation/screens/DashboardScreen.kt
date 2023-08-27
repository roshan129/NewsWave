@file:OptIn(ExperimentalMaterial3Api::class)

package com.roshanadke.dahsboard.presentation.screens

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
import com.roshanadke.dahsboard.presentation.viewmodel.NewsDashboardViewModel
import com.roshanadke.dashboard.domain.model.Article

@Composable
fun DashboardScreen(
    navController: NavController,
    viewModel: NewsDashboardViewModel = hiltViewModel(),
) {

    val newsListState = viewModel.newsListState.value

    val selectedChipItem = viewModel.selectedChipItem.value

    Box {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            val chipItemList = listOf(
                "Tech", "Science", "Business", "Entertainment",
                "Sports", "Health", "Politics", "Travel", "Fashion", "Food"
            )
            LazyRow(
                Modifier.padding(start = 8.dp, end = 8.dp, top = 12.dp),
            ) {

                items(chipItemList) { item ->
                    ChipsItems(
                        chipItem = item,
                        selectedItem = selectedChipItem,
                        onChipItemSelected = { chipItem ->
                            if (chipItem != selectedChipItem) {
                                viewModel.setSelectedChipItem(chipItem)
                                viewModel.setPageNumber(1)
                                viewModel.fetchInitialNewsDashboardList(
                                    selectedChipItem,
                                    STARTING_PAGE_INDEX
                                )

                            }
                        }
                    )
                }

            }
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



            LazyColumn(state = scrollState) {
                items(newsListState.articles) {

                    NewsItemCard(
                        it,
                        onNewsItemClicked = {
                            navController.currentBackStackEntry?.savedStateHandle?.apply {
                                set(KEY_ARTICLE, it)
                            }
                            navController.navigate(Screen.DetailsScreen.route)
                        }
                    )

                }
            }
        }

        if (newsListState.isLoading) {
            ShowProgressBar()
        }

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

