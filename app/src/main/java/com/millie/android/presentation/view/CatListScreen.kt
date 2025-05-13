package com.millie.android.presentation.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.millie.android.presentation.viewmodel.CatListViewModel
import com.millie.domain.model.CatImage
import timber.log.Timber

@Composable
fun CatListScreen(
    viewModel: CatListViewModel = hiltViewModel(),
    onItemClick: (CatImage) -> Unit
) {
    val catItems = viewModel.catData.collectAsLazyPagingItems()
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    val columns = if (isPortrait) 1 else 3

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues()),
            text = "현재 방향: ${if (isPortrait) "세로" else "가로"}",
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(500),
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        )

        when (catItems.loadState.refresh) {
            is LoadState.Loading -> {
                Timber.d("### LoadState.Loading")
                LazyVerticalGrid(
                    columns = GridCells.Fixed(columns),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                        .height(64.dp),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(10) { index ->
                        CatItemSkeleton(modifier = Modifier.fillMaxWidth())
                    }
                }
            }

            is LoadState.Error -> {
                Timber.d("### LoadState.Error")
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Button(onClick = { catItems.retry() }) { Text(text = "데이터 요청 재시도") }
                }
            }

            else -> {
                Timber.d("### LoadState else")
                LazyVerticalGrid(
                    columns = GridCells.Fixed(columns),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                        .height(64.dp),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(catItems.itemCount) { index ->
                        catItems[index]?.let { catImage ->
                            CatItem(
                                catImage = catImage,
                                onItemClick = onItemClick,
                                imageCacheManager = viewModel.imageCacheManager
                            )
                        }
                    }

                    if (catItems.loadState.append is LoadState.Loading) {
                        items(3) {
                            CatItemSkeleton(modifier = Modifier.fillMaxWidth())
                        }
                    }
                }
            }
        }
    }

}

