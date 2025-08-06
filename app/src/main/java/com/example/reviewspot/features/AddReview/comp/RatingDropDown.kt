package com.example.reviewspot.features.AddReview.comp

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
import com.example.reviewspot.ReviewViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingDropDown(viewModel: ReviewViewModel) {

    val options = listOf(0,1,2,3,4,5)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ExposedDropdownMenuBox(
            expanded = viewModel.ratingExpanded.value,
            onExpandedChange = {
                viewModel.ratingExpanded.value = !viewModel.ratingExpanded.value
            }
        ) {
            TextField(
                value = viewModel.ratingSelected.value.toString(),
                onValueChange = {},
                label = { Text(text = "Rating") },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = viewModel.ratingExpanded.value
                    )
                },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = viewModel.ratingExpanded.value,
                onDismissRequest = { viewModel.ratingExpanded.value = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(text = option.toString()) },
                        onClick = {
                            viewModel.ratingExpanded.value = false
                            viewModel.ratingSelected.value = option
                        }
                    )

                }
            }
        }
    }
}