package com.example.reviewspot.features.home.comp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.reviewspot.ReviewViewModel
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
        Column(
            modifier = Modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Item ID : ${item.itemID}",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Item Name : ${item.itemName}",
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "Item Type: ${item.itemType}",
                style = MaterialTheme.typography.bodyMedium
            )

            Row(
                modifier = Modifier.align(Alignment.End),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = {
                    // Navigate to item detail/review screen
                    nav.navigate("itemDetail/${item.itemID}")
                }) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "View Details"
                    )
                }
            }
        }
    }
}
