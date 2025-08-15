package com.example.reviewspot.features.myReviews.comp

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.features.room.Item
import com.example.reviewspot.features.room.Review
import com.example.reviewspot.features.room.User
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource

@Composable
fun ReviewCard(review: Review, viewModel: ReviewViewModel) {

    var item = remember { mutableStateOf<Item?>(null) }
    var user = remember { mutableStateOf<User?>(null) }

    LaunchedEffect(key1 = review.itemID) {
        item.value = viewModel.getItemByID(review.itemID)
        user.value = viewModel.getUserById(review.userID)
    }

    // Wrapped all content in a Card
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = com.example.reviewspot.R.color.light_blue_card) // Light pastel background for the card
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // Added elevation for separation
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = item.value?.itemImage ?: android.R.drawable.ic_menu_report_image),
                    contentDescription = stringResource(id = com.example.reviewspot.R.string.item_image),
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = item.value?.itemName ?: stringResource(id = com.example.reviewspot.R.string.loading),
                    style = MaterialTheme.typography.titleMedium,
                    color = colorResource(id = com.example.reviewspot.R.color.text_dark),
                    maxLines = 1
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Review text full width
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = colorResource(id = com.example.reviewspot.R.color.accent_blue),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(
                        color = colorResource(id = R.color.white), // Using white for the inner text box background
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(12.dp)
            ) {
                Text(
                    text = review.reviewText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = com.example.reviewspot.R.color.text_dark)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Rating full width under review
            Text(
                text = stringResource(id = com.example.reviewspot.R.string.rating, review.rating),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color =  colorResource(id = com.example.reviewspot.R.color.medium_blue)
            )

            Spacer(modifier = Modifier.height(4.dp))

            //Star rating display
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                for (i in 1..5) {
                    Icon(
                        imageVector = if (i <= review.rating) Icons.Filled.Star else Icons.Outlined.Star,
                        contentDescription = stringResource(id = com.example.reviewspot.R.string.star_count, i),
                        tint = if (i <= review.rating) colorResource(id = com.example.reviewspot.R.color.yellow) else colorResource(id = com.example.reviewspot.R.color.gray),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // User name small
            Text(
                text = stringResource(
                    id = com.example.reviewspot.R.string.by_user,
                    user.value?.firstName.orEmpty(),
                    user.value?.lastName.orEmpty()
                ),
                style = MaterialTheme.typography.bodySmall,
                color = colorResource(id = com.example.reviewspot.R.color.medium_blue)
            )
            Spacer(modifier = Modifier.height(4.dp))

            // Time at bottom right
            Row(
                modifier = Modifier.fillMaxWidth(),
                //horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "${formatDate(review.timestamp)} - ${
                        com.example.reviewspot.features.itemInfo.comp.formatTime(
                            review.timestamp
                        )
                    }",
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(id = com.example.reviewspot.R.color.medium_blue)
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(8.dp))
}

fun formatDate(timestamp: Long): String {
    val date = java.text.SimpleDateFormat("dd MMM yyyy", java.util.Locale.getDefault())
    return date.format(java.util.Date(timestamp))
}

fun formatTime(timestamp: Long): String {
    val time = java.text.SimpleDateFormat("hh:mm a", java.util.Locale.getDefault())
    return time.format(java.util.Date(timestamp))
}