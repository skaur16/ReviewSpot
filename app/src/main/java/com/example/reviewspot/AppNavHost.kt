package com.example.reviewspot

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.reviewspot.features.AddReview.AddReviewScreen
import com.example.reviewspot.features.addItem.AddItemScreen
import com.example.reviewspot.features.home.HomeScreen
import com.example.reviewspot.features.itemInfo.ItemInfoScreen
import com.example.reviewspot.features.logout.LogOutScreen
import com.example.reviewspot.features.myReviews.MyReviewScreen


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

        composable(Screens.AddReview.name) {
            //add review screen
            AddReviewScreen(nav,viewModel)
        }

        composable(Screens.MyReviews.name) {
            //my reviews screen
            MyReviewScreen(viewModel)
        }

        composable(Screens.ItemInfo.name){
            viewModel.itemInfo.value?.let { it -> ItemInfoScreen(viewModel, it, nav) }
        }

        composable(AuthScreens.Logout.name){
            LogOutScreen(nav, viewModel)

        }





    }
}