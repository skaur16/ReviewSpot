package com.example.reviewspot.features.itemInfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.features.home.comp.ItemCard
import com.example.reviewspot.features.myReviews.comp.ReviewCard
import com.example.reviewspot.features.room.Item

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemInfoScreen(viewModel: ReviewViewModel, item : Item, nav : NavController) {


   LaunchedEffect(key1 = Unit){
       viewModel.getReviewsByItemId(item.itemID)
   }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Item Info")
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            ItemCard(item, nav = nav, viewModel = viewModel)

            Text(text = "Reviews : ")

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                items(viewModel.itemReviews.value){
                    ReviewCard(review = it, viewModel = viewModel)
                }
            }
        }
    }
}