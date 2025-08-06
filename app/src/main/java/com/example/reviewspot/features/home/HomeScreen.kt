package com.example.reviewspot.features.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.Screens
import com.example.reviewspot.features.home.comp.ItemCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: ReviewViewModel, nav : NavController) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllItems()
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Home") })
        },
        floatingActionButton = {
            //floating action button
            IconButton(
                onClick = {nav.navigate(Screens.AddItems.name)}
            ){
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Item"
                )
            }
        }
    )
    {
       LazyColumn(modifier = Modifier.padding(it)){
           items(viewModel.allItems.value){item->
               ItemCard(item, nav, viewModel)
           }
       }
    }
}