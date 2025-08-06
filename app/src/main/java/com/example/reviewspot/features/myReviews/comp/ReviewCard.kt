package com.example.reviewspot.features.myReviews.comp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    Card(){
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



        }
    }
}