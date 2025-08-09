package com.example.reviewspot.features.myReviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.Screens
import com.example.reviewspot.features.myReviews.comp.ReviewCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyReviewScreen(viewModel : ReviewViewModel, nav : NavController) {
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
        },
        floatingActionButton = {
            //floating action button
            IconButton(
                onClick = {nav.navigate(Screens.AddReview.name)}
            ){
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.primary, // or any color you want
                    tonalElevation = 4.dp, // subtle shadow effect
                    modifier = Modifier.padding(4.dp) // padding inside the IconButton
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Item",
                        tint = Color.White, // make icon white for contrast
                        modifier = Modifier.padding(8.dp) // padding inside the box
                    )
                }
            }
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