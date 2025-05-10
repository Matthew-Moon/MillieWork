package com.millie.android.presentation.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.millie.android.domain.model.CatImage
import com.millie.android.presentation.view.CatListScreen
import com.millie.android.presentation.view.WebViewScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    val gson = remember { Gson() }

    NavHost(navController = navController, startDestination = "catList") {
        composable("catList") {
            CatListScreen(onItemClick = { catImage ->
                val json = Uri.encode(gson.toJson(catImage))  // 직렬화 + encode
                navController.navigate("webView/$json")
            })
        }

        composable(
            "webView/{cat}",
            arguments = listOf(navArgument("cat") { type = NavType.StringType })
        ) { backStackEntry ->
            val json = backStackEntry.arguments?.getString("cat") ?: ""
            val decoded = Uri.decode(json)
            val catImage = gson.fromJson(decoded, CatImage::class.java)  // 역직렬화

            WebViewScreen(catImage = catImage) {
                navController.popBackStack()
            }
        }
    }
}