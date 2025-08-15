package com.example.reviewspot.features.AddReview

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reviewspot.R
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.features.AddReview.comp.ItemName
import com.example.reviewspot.features.addItem.comp.ItemType
import com.example.reviewspot.features.addItem.comp.ItemTypeList

@Composable
fun StarRating(viewModel: ReviewViewModel) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        for (i in 1..5) {
            val isSelected = i <= viewModel.rating.value
            Icon(
                imageVector = if (isSelected) Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = "Rate $i star",
                tint = if (isSelected) Color.Yellow else Color.Gray,
                modifier = Modifier
                    .size(40.dp)
                    .clickable { viewModel.rating.value = i }
            )
        }
    }
}

@Composable
fun AddReviewScreen(nav: NavController, viewModel: ReviewViewModel) {

    LaunchedEffect(key1 = viewModel.itemTypeSelected.value) {
        viewModel.itemNameSelected.value = ""
        viewModel.getItemsByType()
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFCAF0F8))
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.ChooseItem),
            color = Color(0xFF03045E),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        ItemType(viewModel, ItemTypeList.entries)

        ItemName(viewModel, viewModel.itemsListByType.value)

        // ⭐ Star rating instead of dropdown
        Text(text = "Rate this item:", color = Color(0xFF03045E))
        StarRating(viewModel)

        OutlinedTextField(
            value = viewModel.reviewText.value,
            onValueChange = { viewModel.reviewText.value = it },
            label = { Text(text = stringResource(id = R.string.ReviewText)) },
            singleLine = false,
            minLines = 3,
            maxLines = 10,
            modifier = Modifier.fillMaxWidth(),
        )

        val pleaseSelectAnItem = stringResource(id = R.string.Pleaseselectanitem)
        val reviewTextCannotBeEmpty = stringResource(id = R.string.ReviewTextcannotbeempty)
        val reviewAddedSuccessfully = stringResource(id = R.string.ReviewAddedsuccessfully)
        val errorAddingReview = stringResource(id = R.string.Erroraddingreview)

        Button(
            onClick = {
                if (viewModel.itemNameSelected.value.isBlank()) {
                    Toast.makeText(context, pleaseSelectAnItem, Toast.LENGTH_LONG).show()
                } else if (viewModel.reviewText.value.isBlank()) {
                    Toast.makeText(context, reviewTextCannotBeEmpty, Toast.LENGTH_LONG).show()
                } else if (viewModel.rating.value == 0) {
                    Toast.makeText(context, "Please select a rating", Toast.LENGTH_LONG).show()
                } else {
                    val onSuccess = {
                        Toast.makeText(context, reviewAddedSuccessfully, Toast.LENGTH_LONG).show()
                    }
                    val onError = {
                        Toast.makeText(context, errorAddingReview, Toast.LENGTH_LONG).show()
                    }
                    if (viewModel.loggedInUserFound.value) {
                        viewModel.findItemByTypeAndName {
                            viewModel.addReview(onSuccess, onError)
                        }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0077B6)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.AddReview), color = Color.White)
        }
    }
}
