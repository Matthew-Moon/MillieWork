package com.millie.android.presentation.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.millie.android.data.utils.ImageCacheManager
import com.millie.android.domain.model.CatImage
import timber.log.Timber
import java.io.File

@Composable
fun CatItem(
    catImage: CatImage?,
    onItemClick: (CatImage) -> Unit,
    imageCacheManager: ImageCacheManager
) {
    val isNetwork = rememberIsNetwork()

    catImage?.let { catImage ->
        val image = remember(catImage, isNetwork) {
            if (isNetwork) {
                Timber.d("### 서버 이미지 ==> ${catImage.url}")
                catImage.url
            } else {
                val file = File(imageCacheManager.getFilePath(catImage.id))
                Timber.d("### 로컬 이미지 ==> $file")
                file
            }
        }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .build(),
            contentDescription = "고양이 사진",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .clickable { onItemClick(catImage) }
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))
        )

    }
}

@Composable
fun rememberIsNetwork(): Boolean {
    val context = LocalContext.current
    val connectivityManager = remember {
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
    val network = connectivityManager.activeNetwork
    val capabilities = connectivityManager.getNetworkCapabilities(network)
    return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}