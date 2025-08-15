package com.example.reviewspot.features.itemInfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reviewspot.R
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.features.itemInfo.comp.ItemInfoCard
import com.example.reviewspot.features.myReviews.comp.ReviewCard
import com.example.reviewspot.features.room.Item

@Composable
fun ItemInfoScreen(viewModel: ReviewViewModel, item: Item, nav: NavController) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getReviewsByItemId(item.itemID)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background_light_blue)) // Lightest blue background
            .padding(16.dp)
    ) {
        // Fixed item info at top
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Item image - rounded corners with size
            Image(
                painter = painterResource(id = item.itemImage),
                contentDescription =  stringResource(id = R.string.item_image_desc),
                modifier = Modifier
                    .size(140.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Text(
                text = item.itemName,
                style = MaterialTheme.typography.headlineMedium,
                color = colorResource(id = R.color.deep_navy)
            )
            Text(
                text = item.itemType.name,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.medium_blue)
            )
        }

        // Separation line
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            thickness = 1.dp,
            color = colorResource(id = R.color.divider_blue)
        )

        // Reviews header
        Text(
            text = stringResource(id = R.string.reviews),
            style = MaterialTheme.typography.headlineSmall,
            color = colorResource(id = R.color.deep_navy),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Scrollable list of reviews filling remaining space
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (viewModel.itemReviews.value.isEmpty()) {
                item {
                    Text(
                        text =stringResource(id = R.string.no_reviews_yet),
                        modifier = Modifier.padding(16.dp),
                        color =  colorResource(id = R.color.accent_blue)
                    )
                }
            } else {
                items(viewModel.itemReviews.value) { review ->
                    ItemInfoCard(review = review, viewModel = viewModel)
                }
            }
        }
    }
}
