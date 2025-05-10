package com.millie.android.presentation.view

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun CatListScreen(catImageUrls: List<String>, onItemClick: (String) -> Unit) {
    Column {
        val configuration = LocalConfiguration.current
        val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        val columns = if (isPortrait) 1 else 3

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(catImageUrls) { imageUrl ->
                CatItem(imageUrl, onItemClick)
            }
        }
    }
}

@Preview
@Composable
fun CatListScreenPreview() {
    CatListScreen(catImageUrls, {})
}

val catImageUrls = listOf(
    "https://cdn2.thecatapi.com/images/11g.jpg",
    "https://cdn2.thecatapi.com/images/9u9.jpg",
    "https://cdn2.thecatapi.com/images/a85.jpg",
    "https://cdn2.thecatapi.com/images/aka.gif",
    "https://cdn2.thecatapi.com/images/d44.jpg",
    "https://cdn2.thecatapi.com/images/d77.jpg",
    "https://cdn2.thecatapi.com/images/MTQ5NTAyMA.jpg",
    "https://cdn2.thecatapi.com/images/MTY0MDgwOA.jpg",
    "https://cdn2.thecatapi.com/images/MTcwOTI4NQ.jpg",
    "https://cdn2.thecatapi.com/images/MTczNzcxNA.jpg"
)

@Preview(
    name = "Vertical (Portrait)",
    widthDp = 360,
    heightDp = 640,
    showBackground = true
)
@Composable
fun VerticalPreview() {
    CatListScreen(catImageUrls, {})
}

@Preview(
    name = "Horizontal (Landscape)",
    widthDp = 640,
    heightDp = 360,
    showBackground = true
)
@Composable
fun HorizontalPreview() {
    CatListScreen(catImageUrls, {})
}