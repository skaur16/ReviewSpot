package com.example.reviewspot.features.myReviews

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.features.myReviews.comp.ReviewCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyReviewScreen(viewModel : ReviewViewModel) {
    LaunchedEffect (key1 = Unit) {
        viewModel.getMyReviews()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "My Reviews")
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(viewModel.myReviews.value){
                ReviewCard(it, viewModel)
            }
        }
    }


}