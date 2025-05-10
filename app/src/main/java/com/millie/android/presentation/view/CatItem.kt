package com.millie.android.presentation.view

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import timber.log.Timber

@Composable
fun CatItem(catImages: List<String>) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    val columns = if (isPortrait) 1 else 3

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = Modifier.fillMaxSize().background(Color.White),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(catImages) { imageUrl ->
            AsyncImage(
                model = imageUrl,
                contentDescription = "고양이 사진",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .clickable {
                        Timber.d("### 클릭!!!!!")
                    }
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(12.dp))
            )
        }
    }
}

@Preview(
    name = "Vertical (Portrait)",
    widthDp = 360,
    heightDp = 640,
    showBackground = true
)
@Composable
fun VerticalPreview() {
    CatItem(catImageUrls)
}

@Preview(
    name = "Horizontal (Landscape)",
    widthDp = 640,
    heightDp = 360,
    showBackground = true
)
@Composable
fun HorizontalPreview() {
    CatItem(catImageUrls)
}