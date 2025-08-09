package com.example.reviewspot.features.addItem.comp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.reviewspot.ReviewViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemType(
    viewModel: ReviewViewModel,
    options: List<ItemTypeList>,
    modifier: Modifier = Modifier
) {
    //Dropdown to display item types
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ExposedDropdownMenuBox(
            expanded = viewModel.itemTypeListExpanded.value,
            onExpandedChange = {
                viewModel.itemTypeListExpanded.value = !viewModel.itemTypeListExpanded.value
            }
        ) {
            TextField(
                value = viewModel.itemTypeSelected.value.name,
                onValueChange = {},
                label = { Text(text = "Item Type") },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = viewModel.itemTypeListExpanded.value
                    )
                },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = viewModel.itemTypeListExpanded.value,
                onDismissRequest = { viewModel.itemTypeListExpanded.value = false }
            ) {
                options.forEach { itemType ->
                    DropdownMenuItem(
                        text = { Text(text = itemType.name) },
                        onClick = {
                            viewModel.itemTypeListExpanded.value = false
                            viewModel.itemTypeSelected.value = itemType
                        }
                    )

                }
            }
        }
    }


}