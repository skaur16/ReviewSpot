package com.example.reviewspot

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reviewspot.features.login.LoginScreen
import com.example.reviewspot.features.login.RegisterScreen

@Composable
fun AuthNavHost(viewModel : ReviewViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AuthScreens.Login.name){
        composable(AuthScreens.Login.name){
            LoginScreen(viewModel, navController)
        }
        composable(AuthScreens.Register.name){
            RegisterScreen(viewModel, navController)
        }


    }

}