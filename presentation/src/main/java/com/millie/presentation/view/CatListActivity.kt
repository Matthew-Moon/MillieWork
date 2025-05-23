package com.millie.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.millie.presentation.navigation.AppNavHost
import com.millie.presentation.theme.MillieApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatListActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MillieApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    AppNavHost(navController = navController)
                }
            }
        }
    }
}

