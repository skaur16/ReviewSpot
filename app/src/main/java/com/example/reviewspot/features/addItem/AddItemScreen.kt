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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reviewspot.R
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.Screens
import com.example.reviewspot.features.addItem.comp.ItemType
import com.example.reviewspot.features.addItem.comp.ItemTypeList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemScreen(viewModel: ReviewViewModel, nav: NavController) {

    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel.itemTypeSelected.value) {
        viewModel.getItemImage()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.lightest_blue)) // Lightest blue background
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        Image(
            painter = painterResource(id = viewModel.itemImage.intValue),
            contentDescription = stringResource(R.string.ItemImage),
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(3.dp, colorResource(id = R.color.accent_blue), CircleShape) // Accent blue border
                .shadow(8.dp, CircleShape),
            contentScale = ContentScale.Crop
        )

        OutlinedTextField(
            value = viewModel.itemName.value,
            onValueChange = { viewModel.itemName.value = it },
            label = { Text(text = stringResource(id = R.string.ItemName)) },
            singleLine = true
        )

        ItemType(
            viewModel,
            ItemTypeList.entries
        )

        val itemAdded = stringResource(id = R.string.ItemAdded)
        Button(
            onClick = {
                viewModel.addItem {
                    Toast.makeText(
                        context,
                        itemAdded,
                        Toast.LENGTH_SHORT
                    ).show()

                    viewModel.itemName.value = ""
                    viewModel.itemTypeSelected.value = ItemTypeList.Movie
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.medium_blue) // Medium blue button
            )
        ) {
            Text(
                text = stringResource(id = R.string.AddItem),
                color = Color.White // White text for contrast
            )
        }
    }
}
