package com.example.genshincompose.ui.page

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.genshincompose.DetailPoster
import com.example.genshincompose.ui.page.HomePoster
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun Main() {
    val navController = rememberNavController()
    ProvideWindowInsets {
        NavHost(
            navController = navController, startDestination = "Home"
        ) {
            composable(
                route = "Home",
            ){
                HomePoster(navController)
            }

            composable("detail/{objectId}"){
                val objectId = it.arguments?.getString("objectId")
                DetailPoster(objectId){
                    navController.popBackStack()
                }
            }
        }
    }
}

