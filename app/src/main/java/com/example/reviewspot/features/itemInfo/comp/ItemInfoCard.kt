package com.example.reviewspot.features.itemInfo.comp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.reviewspot.R
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.features.myReviews.comp.formatDate
import com.example.reviewspot.features.room.Review
import com.example.reviewspot.features.room.User
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ItemInfoCard(review: Review, viewModel: ReviewViewModel) {

    var user = remember { mutableStateOf<User?>(null) }

    LaunchedEffect(key1 = review.userID) {
        user.value = viewModel.getUserById(review.userID)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.soft_pastel_blue) // Light pastel background for the card
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Spacer(modifier = Modifier.height(8.dp))

            // Review text
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = colorResource(id = R.color.border_blue),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(12.dp)
            ) {
                Text(
                    text = review.reviewText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = R.color.deep_navy)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Numeric rating
            Text(
                text = stringResource(id = R.string.rating, review.rating),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.accent_blue)
            )

            //Star rating display
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                for (i in 1..5) {
                    Icon(
                        imageVector = if (i <= review.rating) Icons.Filled.Star else Icons.Outlined.Star,
                        contentDescription = "$i Star",
                        tint = if (i <= review.rating) colorResource(id = R.color.yellow) else colorResource(id = R.color.gray),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // User name
            Text(
                text = stringResource(
                    id = R.string.by_user,
                    user.value?.firstName.orEmpty(),
                    user.value?.lastName.orEmpty()
                ),
                style = MaterialTheme.typography.bodySmall,
                color =  colorResource(id = R.color.medium_blue),
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Time at bottom right
            Row(
                modifier = Modifier.fillMaxWidth(),
                //horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "${formatDate(review.timestamp)} - ${formatTime(review.timestamp)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(id = R.color.medium_blue)
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(8.dp))
}

fun formatTime(timestamp: Long): String {
    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return sdf.format(Date(timestamp))
}

fun formatDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return sdf.format(Date(timestamp))
}
