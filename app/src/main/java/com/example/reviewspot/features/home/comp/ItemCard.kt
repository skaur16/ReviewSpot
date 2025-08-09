package com.example.reviewspot.features.home.comp

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.Screens
import com.example.reviewspot.features.itemInfo.ItemInfoScreen
import com.example.reviewspot.features.room.Item

@Composable
fun ItemCard(item: Item, nav: NavController, viewModel: ReviewViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                viewModel.itemInfo.value = item
                nav.navigate("itemInfo")
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Round Image on left
            Image(
                painter = painterResource(id = item.itemImage),
                contentDescription = "Item Image",
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)  // Perfect circle
            )


            // Spacer between image and texts
            Spacer(modifier = Modifier.width(12.dp))

            // Column with Item Name and Type
            Column(
                modifier = Modifier.weight(1f) // Take remaining width
            ) {
                Text(
                    text = item.itemName,
                    style = MaterialTheme.typography.titleMedium, // Larger text for name
                    maxLines = 1
                )
                Text(
                    text = item.itemType.name,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant // smaller & lighter
                )
            }

            // Info icon aligned vertically center on right
            IconButton(
                onClick = {
                    nav.navigate(Screens.ItemInfo.name)
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "View Details"
                )
            }
        }
    }
}
