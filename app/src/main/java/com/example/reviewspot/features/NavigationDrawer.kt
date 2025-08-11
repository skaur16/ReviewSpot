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
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
                drawerContainerColor = androidx.compose.ui.graphics.Color(0xFFCAF0F8) // lightest blue background
            ) {
                // Header with image
                Image(
                    painter = painterResource(id = R.drawable.review),
                    contentDescription = "Header Image",
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
                                tint = androidx.compose.ui.graphics.Color(0xFF0077B6) // medium blue
                            )
                        },
                        label = {
                            Text(
                                item,
                                color = androidx.compose.ui.graphics.Color(0xFF03045E) // dark blue text
                            )
                        },
                        selected = false,
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedContainerColor = androidx.compose.ui.graphics.Color.Transparent,
                            selectedContainerColor = androidx.compose.ui.graphics.Color(0xFFADE8F4) // light selection
                        ),
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate(item)
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        thickness = 1.dp,
                        color = Color(0xFF90E0EF) // soft blue from your palette
                    )
                }

                Spacer(modifier = Modifier.weight(1f))


                // Logout item
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = null,
                            tint = androidx.compose.ui.graphics.Color(0xFF0077B6)
                        )
                    },
                    label = {
                        Text(
                            "Log Out",
                            color = androidx.compose.ui.graphics.Color(0xFF03045E)
                        )
                    },
                    selected = false,
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = androidx.compose.ui.graphics.Color.Transparent,
                        selectedContainerColor = androidx.compose.ui.graphics.Color(0xFFADE8F4) // light selection
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
                    color = Color(0xFF90E0EF) // soft blue from your palette
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "ReviewSpot",
                            color = androidx.compose.ui.graphics.Color.White
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = androidx.compose.ui.graphics.Color.White
                            )
                        }
                    },
                    colors = androidx.compose.material3.TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = androidx.compose.ui.graphics.Color(0xFF023E8A) // strong navy blue
                    )
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(androidx.compose.ui.graphics.Color(0xFF90E0EF)) // soft blue background
            ) {
                AppNavHost(navController, viewModel)
            }
        }
    }
}
