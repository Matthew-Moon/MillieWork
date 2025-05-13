package com.millie.presentation.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.millie.presentation.view.CatItemSkeleton

@Composable
fun ImageWithSkeleton(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    val shimmerVisible = remember { mutableStateOf(true) }

    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(12.dp))
    ) {
        if (shimmerVisible.value) CatItemSkeleton(modifier = Modifier.matchParentSize())

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .build(),
            contentDescription = "고양이 사진",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .matchParentSize()
                .alpha(if (shimmerVisible.value) 0f else 1f),
            onState = { state ->
                when (state) {
                    is AsyncImagePainter.State.Success -> shimmerVisible.value = false
                    is AsyncImagePainter.State.Error -> shimmerVisible.value = false
                    else -> {}
                }
            }
        )
    }
}
