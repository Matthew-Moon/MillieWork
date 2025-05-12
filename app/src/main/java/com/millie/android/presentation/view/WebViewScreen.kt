package com.millie.android.presentation.view

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.millie.android.data.utils.ImageCacheManager
import com.millie.android.domain.model.CatImage
import timber.log.Timber
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(
    catImage: CatImage,
    imageCacheManager: ImageCacheManager,
    onBack: () -> Unit
) {

    val localFile = remember(catImage.id) {
        val path = imageCacheManager.getFilePath(catImage.id)
        File(path).takeIf { it.exists() }
    }

    val filePath = remember(catImage.id) {
        localFile?.let {
            """
                <html>
                  <body style="margin:0;padding0;">
                    <img src="file://${localFile.absolutePath}" style="width:100%;height:auto;" />
                  </body>
                </html>
            """.trimIndent()
        } ?: run { null }
    }

    Scaffold(
        topBar = {
            MillieTopAppbar(
                title = "고양이 ID: ${catImage.id}",
                onLeftClick = onBack
            )
        }
    ) { paddingValues ->
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    settings.apply {
                        javaScriptEnabled = true
                        builtInZoomControls = true
                        displayZoomControls = false
                        allowFileAccess = true
                        useWideViewPort = true
                        loadWithOverviewMode = true
                        cacheMode = WebSettings.LOAD_DEFAULT
                        setSupportZoom(true)
                    }

                    filePath?.let {
                        Timber.d("### 로컬 이미지 ==> $it")
                        loadDataWithBaseURL(null, it, "text/html", "utf-8", null)
                    } ?: run {
                        Timber.d("### 서버 이미지 ==> ${getHtmlContent(catImage.url)}")
                        loadDataWithBaseURL(null, getHtmlContent(catImage.url), "text/html", "utf-8", null)
                    }
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),

            )
    }
}

/**
 * object-fit: cover 로 바꾸면 이미지가 잘릴 수 있지만 화면을 꽉 채웁니다.
 * object-fit: contain 은 이미지 전체를 보여주고 비율을 유지합니다.
 */
private fun getHtmlContent(url: String): String {
    return """              
        <!DOCTYPE html>
            <html>
            <head>
                <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=3.0, user-scalable=yes">
                <style>
                    html, body {
                        margin: 0;
                        padding: 0;
                        height: 100%;
                        width: 100%;
                        background-color: black;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                    }
                    img {
                        width: 100vw;
                        height: 100vh;
                        object-fit: contain;
                    }
                </style>
            </head>
            <body>
                <img src="$url" />
            </body>
            </html>
    """.trimIndent()
}

// TODO html 서버와로컬 코드 공통 및 정렬 처리 필요함
// 내부 파일 캐시 정책 검토 어떤게 효율적인지?? 일반적인 방법 찾기
