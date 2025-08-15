package com.example.reviewspot.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
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
fun NavigationDrawer(viewModel: ReviewViewModel, mainNavController: NavController) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val homeTitle = stringResource(id = R.string.home)
    var selectedTitle by remember { mutableStateOf(homeTitle) }

    val menuItems = listOf(
        Screens.Home.name,
        Screens.AddItems.name,
        Screens.MyReviews.name,
        Screens.AddReview.name
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(280.dp),
                drawerContainerColor = colorResource(id = R.color.light_blue)
            ) {
                // Header with image
                Image(
                    painter = painterResource(id = R.drawable.review),
                    contentDescription = stringResource(id = R.string.header_image),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Menu Items
                menuItems.forEach { item ->
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = null,
                                tint = colorResource(id = R.color.medium_blue)
                            )
                        },
                        label = {
                            Text(item, color = colorResource(id = R.color.dark_navy))
                        },
                        selected = selectedTitle == item,
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedContainerColor = Color.Transparent,
                            selectedContainerColor = colorResource(id = R.color.soft_pastel_blue)
                        ),
                        onClick = {
                            selectedTitle = item
                            scope.launch { drawerState.close() }
                            navController.navigate(item)
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        thickness = 1.dp,
                        color = colorResource(id = R.color.light_blue_divider)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                // Display logged-in user
                Text(
                    text = "${viewModel.loggedInUser.value?.firstName ?: ""} ${viewModel.loggedInUser.value?.lastName ?: ""}",
                    color = colorResource(id = R.color.dark_navy),
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 8.dp)
                )
                // Logout
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = null,
                            tint = colorResource(id = R.color.medium_blue)
                        )
                    },
                    label = {
                        Text(stringResource(id = R.string.logout), color = colorResource(id = R.color.dark_navy))
                    },
                    selected = false,
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.Transparent,
                        selectedContainerColor = colorResource(id = R.color.soft_pastel_blue)
                    ),
                    onClick = {
                        scope.launch { drawerState.close() }
                        mainNavController.navigate(AuthScreens.Logout.name)
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    thickness = 1.dp,
                    color = colorResource(id = R.color.light_blue_divider)
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(selectedTitle, color = Color.White)
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = stringResource(id = R.string.menu),
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = colorResource(id = R.color.dark_blue)
                    )
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(colorResource(id = R.color.light_blue_divider))
            ) {
                AppNavHost(navController, viewModel)
            }
        }
    }
}

