package com.example.reviewspot.features.myReviews.comp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.features.room.Item
import com.example.reviewspot.features.room.Review
import com.example.reviewspot.features.room.User

@Composable
fun ReviewCard(review : Review, viewModel: ReviewViewModel) {

    var item = remember { mutableStateOf<Item?>(null) }
    var user = remember { mutableStateOf<User?>(null) }

    LaunchedEffect(key1 = review.itemID){
        item.value = viewModel.getItemByID(review.itemID)
        user.value = viewModel.getUserById(review.userID)
    }
    /*Card(){
        Column(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Text(text = "Review ID : ${review.reviewID}")
            val itemName = "Item Name : ${item.value?.itemName}"
            Text(text = itemName)
            val itemType = "Item Type : ${item.value?.itemType}"
            Text(text = itemType)
            Text(text = "Review Text : ${review.reviewText}")
            Text(text = "Rating : ${review.rating}")
            Text(text = "User ID : ${user.value?.userID}")
            Text(text = "User Name : ${user.value?.firstName+" "+user.value?.lastName}")

            Text(text = "Date : ${formatDate(review.timestamp)}")
            Text(text = "Time : ${formatTime(review.timestamp)}")


        }
    }*/

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 6.dp),
        elevation = CardDefaults.cardElevation(3.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            // Date at top center, small font
            Text(
                text = "Date : ${formatDate(review.timestamp)}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.DarkGray,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            // Other info in smaller font except review text
           /* Text(
                text = "Review ID : ${review.reviewID}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )*/

            Text(
                text = "Item Name : ${item.value?.itemName}",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "Item Type : ${item.value?.itemType}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Text(
                text = "Review Text : ${review.reviewText}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 6.dp)
            )

            Text(
                text = "Rating : ${review.rating}",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium
            )

            /*Text(
                text = "User ID : ${user.value?.userID ?: "Unknown"}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )*/

            Text(
                text = "User Name : ${user.value?.firstName.orEmpty()} ${user.value?.lastName.orEmpty()}",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Time at bottom end, small font
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Time : ${formatTime(review.timestamp)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.DarkGray
                )
            }
        }
    }




}
fun formatDate(timestamp: Long): String {
    val date = java.text.SimpleDateFormat("dd MMM yyyy", java.util.Locale.getDefault())
    return date.format(java.util.Date(timestamp))
}

fun formatTime(timestamp: Long): String {
    val time = java.text.SimpleDateFormat("hh:mm a", java.util.Locale.getDefault())
    return time.format(java.util.Date(timestamp))
}
