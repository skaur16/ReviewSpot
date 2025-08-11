package com.example.reviewspot.features.AddReview.comp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.reviewspot.R
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.features.room.Item

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemName(viewModel: ReviewViewModel, options : List<Item>) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ExposedDropdownMenuBox(
            expanded = viewModel.itemsNameListExpanded.value,
            onExpandedChange = {
                viewModel.itemsNameListExpanded.value = !viewModel.itemsNameListExpanded.value
            }
        ) {
            TextField(
                value = viewModel.itemNameSelected.value,
                onValueChange = {},
                label = { Text(text = stringResource(id = R.string.ItemName)) },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = viewModel.itemsNameListExpanded.value
                    )
                },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = viewModel.itemsNameListExpanded.value,
                onDismissRequest = { viewModel.itemsNameListExpanded.value = false }
            ) {
                options.forEach { items ->
                    DropdownMenuItem(
                        text = { Text(text = items.itemName) },
                        onClick = {
                            viewModel.itemsNameListExpanded.value = false
                            viewModel.itemNameSelected.value = items.itemName
                        }
                    )

                }
            }
        }
    }
}