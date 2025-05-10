package com.millie.android.presentation.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.millie.android.presentation.view.CatListScreen
import com.millie.android.presentation.view.WebViewScreen
import com.millie.android.presentation.view.catImageUrls

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "catList") {
        composable("catList") {
            CatListScreen(catImageUrls, onItemClick = { url ->
                navController.navigate("webView/${Uri.encode(url)}")
            })
        }

        composable(
            "webView/{url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: return@composable
            WebViewScreen(url = url) {
                navController.popBackStack()
            }
        }
    }

}