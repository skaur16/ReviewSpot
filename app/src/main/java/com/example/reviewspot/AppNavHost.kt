package com.example.reviewspot

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.reviewspot.features.addItem.AddItemScreen
import com.example.reviewspot.features.home.HomeScreen


@Composable
fun AppNavHost(nav : NavHostController, viewModel: ReviewViewModel) {

    NavHost(navController = nav, startDestination = Screens.Home.name) {
        composable(Screens.Home.name) {
            //home screen
            HomeScreen(viewModel, nav)

        }

        composable(Screens.AddItems.name) {
            //add items screen
            AddItemScreen(viewModel)
        }




    }
}