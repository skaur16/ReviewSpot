package com.example.reviewspot.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.Screens
import com.example.reviewspot.features.home.comp.ItemCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: ReviewViewModel, nav: NavController) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllItems()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Home",
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { nav.navigate(Screens.Home.name) }) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF023E8A) // deep navy
                )
            )
        },
        modifier = Modifier.background(Color(0xFFCAF0F8)), // very light background

        floatingActionButton = {
            IconButton(
                onClick = { nav.navigate(Screens.AddItems.name) }
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFF0077B6), // bright blue
                    tonalElevation = 4.dp,
                    modifier = Modifier.padding(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Item",
                        tint = Color.White,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color(0xFFCAF0F8)) // very light background
        ) {
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(viewModel.allItems.value) { item ->
                    /*Surface(
                        shape = RoundedCornerShape(12.dp),
                        //color = Color(0xFFADE8F4), // light pastel card background
                        tonalElevation = 4.dp
                    ) {*/
                        ItemCard(item, nav, viewModel)
                    //}
                }
            }
        }
    }
}
