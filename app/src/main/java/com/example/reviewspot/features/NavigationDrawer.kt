package com.example.reviewspot.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.reviewspot.AppNavHost
import com.example.reviewspot.AuthScreens
import com.example.reviewspot.R
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.Screens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer( viewModel: ReviewViewModel, mainNavController : NavController) {

    //creating a layout to take over every screen

    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val menuItems = listOf(Screens.Home.name, Screens.AddItems.name, Screens.MyReviews.name, Screens.AddReview.name, AuthScreens.Logout.name)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                // Header with image
                Image(
                    painter = painterResource(id = R.drawable.review),
                    contentDescription = "Header Image",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
                // Menu Items
                Spacer(modifier = Modifier.height(8.dp))
                menuItems.forEach { item ->
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = null
                            )
                        },
                        label = { Text(item) },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            if(item == AuthScreens.Logout.name){
                                mainNavController.navigate(AuthScreens.Logout.name)
                            }
                            navController.navigate(item)
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "ReviewSpot") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ){
                AppNavHost(navController, viewModel)
            }
        }
    }
}