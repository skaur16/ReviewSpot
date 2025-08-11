package com.example.reviewspot.features.addItem

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reviewspot.R
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.Screens
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
            TopAppBar(
                title = { Text(text = "Add Item", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { nav.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF023E8A) // Dark blue top bar
                )
            )
        },
        containerColor = Color(0xFFCAF0F8) // Lightest blue background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            Image(
                painter = painterResource(id = viewModel.itemImage.intValue),
                contentDescription = "Item Image",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(3.dp, Color(0xFF0096C7), CircleShape) // Accent blue border
                    .shadow(8.dp, CircleShape),
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
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0077B6) // Medium blue button
                )
            ) {
                Text(text = "Add Item", color = Color.White) // White text for contrast
            }
        }
    }
}