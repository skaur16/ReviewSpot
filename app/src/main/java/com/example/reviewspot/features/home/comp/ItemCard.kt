package com.example.reviewspot.features.home.comp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.Screens
import com.example.reviewspot.features.room.Item
import androidx.navigation.NavController
import com.example.reviewspot.R

@Composable
fun ItemCard(item: Item, nav: NavController, viewModel: ReviewViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable {
                viewModel.itemInfo.value = item
                nav.navigate("itemInfo")
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.softPastelBlue) // soft pastel from palette
        )
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Round Image
            Image(
                painter = painterResource(id = item.itemImage),
                contentDescription = stringResource(id = R.string.item_image),
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.itemName,
                    style = MaterialTheme.typography.titleMedium,
                    color = colorResource(id = R.color.deepNavy), // deep navy text for titles
                    maxLines = 1
                )
                Text(
                    text = item.itemType.name,
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(id = R.color.mediumBlue)// medium blue for subtitle
                )
            }

            IconButton(
                onClick = { nav.navigate(Screens.ItemInfo.name) },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = stringResource(id = R.string.view_details),
                    tint = colorResource(id = R.color.accentBlue) // accent blue icon
                )
            }
        }
    }
}
