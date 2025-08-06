package com.example.reviewspot.features.addItem

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.features.addItem.comp.ItemType
import com.example.reviewspot.features.addItem.comp.ItemTypeList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemScreen(viewModel: ReviewViewModel) {

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Add Item") })
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            OutlinedTextField(
                value = viewModel.itemName.value,
                onValueChange = {
                    viewModel.itemName.value = it
                },
                label = { Text(text = "Item Name") },
                singleLine = true
            )

            ItemType(viewModel, ItemTypeList.entries)

            Button(onClick = {
                viewModel.addItem{
                    Toast.makeText(context,
                        "Item Added",
                        Toast.LENGTH_SHORT)
                        .show()

                    viewModel.itemName.value = ""
                    viewModel.itemTypeSelected.value = ItemTypeList.Movie
                }
            }){
                Text(text = "Add Item")
            }
        }
    }
}