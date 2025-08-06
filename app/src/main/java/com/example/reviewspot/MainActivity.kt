package com.example.reviewspot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reviewspot.features.LoadingScreen
import com.example.reviewspot.features.NavigationDrawer
import com.example.reviewspot.features.login.LoginScreen
import com.example.reviewspot.features.login.RegisterScreen
import com.example.reviewspot.features.logout.LogOutScreen
import com.example.reviewspot.ui.theme.ReviewSpotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val viewModel: ReviewViewModel by viewModels()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReviewSpotTheme {
                val mainNavController = rememberNavController()
                var startDestination = remember { mutableStateOf(AuthScreens.Loading.name) }

                LaunchedEffect(key1 = viewModel.loggedInUserFound.value) {
                    viewModel.getLoggedInUser()

                    if(viewModel.loggedInUserFound.value){
                        startDestination.value = AuthScreens.Navigation.name
                    }
                    else{
                        startDestination.value = AuthScreens.Login.name
                    }

                }





                NavHost(navController = mainNavController, startDestination = startDestination.value){
                    composable(AuthScreens.Login.name){
                        LoginScreen(viewModel, mainNavController)
                    }
                    composable(AuthScreens.Register.name){
                        RegisterScreen(viewModel, mainNavController)
                    }
                    composable(AuthScreens.Navigation.name){
                        NavigationDrawer(viewModel, mainNavController)
                    }
                    composable(AuthScreens.Loading.name){
                        LoadingScreen()
                    }
                    composable(AuthScreens.Logout.name){
                        LogOutScreen(mainNavController, viewModel)
                    }
                }

            }
        }
    }
}




