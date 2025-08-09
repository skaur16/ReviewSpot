package com.example.reviewspot.features.itemInfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.features.home.comp.ItemCard
import com.example.reviewspot.features.myReviews.comp.ReviewCard
import com.example.reviewspot.features.room.Item

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemInfoScreen(viewModel: ReviewViewModel, item: Item, nav: NavController) {

    LaunchedEffect(key1 = Unit) {
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
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Fixed item info at top
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Item image - rounded corners with size
                androidx.compose.foundation.Image(
                    painter = painterResource(id = item.itemImage),
                    contentDescription = "Item Image",
                    modifier = Modifier.size(140.dp)
                        .clip(androidx.compose.foundation.shape.RoundedCornerShape(12.dp)),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                )

                Text(
                    text = item.itemName,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = item.itemType.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Separation line
            androidx.compose.material3.Divider(
                modifier = Modifier.padding(horizontal = 16.dp),
                thickness = 1.dp
            )

            // Reviews header
            Text(
                text = "Reviews",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
            )

            // Scrollable list of reviews filling remaining space
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    if (viewModel.itemReviews.value.isEmpty()) {
                        item {
                            Text("No Reviews Yet", modifier = Modifier.padding(16.dp))
                        }
                    } else {
                        items(viewModel.itemReviews.value) { review ->
                            ReviewCard(review = review, viewModel = viewModel)
                        }
                    }
                }
            )
        }
    }
}