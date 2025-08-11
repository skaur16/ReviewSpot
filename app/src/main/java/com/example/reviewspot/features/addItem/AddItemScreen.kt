package com.example.reviewspot.features.addItem

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reviewspot.R
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.features.addItem.comp.ItemType
import com.example.reviewspot.features.addItem.comp.ItemTypeList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemScreen(viewModel: ReviewViewModel, nav : NavController) {

    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel.itemTypeSelected.value) {
        viewModel.getItemImage()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Add Item") })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 24.dp, vertical = 16.dp), // Added horizontal padding for nicer margin
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)  // Increase spacing between elements
        ) {

            Image(
                painter = painterResource(id = viewModel.itemImage.intValue),
                contentDescription = "Item Image",
                modifier = Modifier
                    .size(120.dp) // Slightly bigger image for better visibility
                    .clip(CircleShape)
                    .border(3.dp, Color.Gray.copy(alpha = 0.5f), CircleShape) // lighter border
                    .shadow(8.dp, CircleShape), // stronger shadow for depth
                contentScale = ContentScale.Crop
            )

            OutlinedTextField(
                value = viewModel.itemName.value,
                onValueChange = {
                    viewModel.itemName.value = it
                },
                label = { Text(text = "Item Name") },
                singleLine = true
            )

            ItemType(
                viewModel,
                ItemTypeList.entries
            )

            Button(
                onClick = {
                    viewModel.addItem {
                        Toast.makeText(
                            context,
                            "Item Added",
                            Toast.LENGTH_SHORT
                        ).show()

                        viewModel.itemName.value = ""
                        viewModel.itemTypeSelected.value = ItemTypeList.Movie
                    }
                }
            ) {
                Text(text = "Add Item")
            }
        }
    }
}