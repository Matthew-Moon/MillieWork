package com.millie.android.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CatItem(
    imageUrl: String,
    onItemClick: (String) -> Unit
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "고양이 사진",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .clickable {
                onItemClick(imageUrl)
            }
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(12.dp))
    )
}

