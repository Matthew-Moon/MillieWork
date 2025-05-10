package com.millie.android.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.millie.android.presentation.theme.MillieApplicationTheme
import com.millie.android.presentation.viewmodel.CatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatActivity : ComponentActivity() {
    private val viewModel: CatViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MillieApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CatItem(catImageUrls)
                }
            }
        }
//        viewModel.getImagesSearch()

    }
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
