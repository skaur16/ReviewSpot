package com.example.reviewspot.features.AddReview

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TextFieldDefaults
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
import com.example.reviewspot.features.AddReview.comp.RatingDropDown
import com.example.reviewspot.features.addItem.comp.ItemType
import com.example.reviewspot.features.addItem.comp.ItemTypeList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReviewScreen(nav: NavController, viewModel: ReviewViewModel) {

    LaunchedEffect(key1 = viewModel.itemTypeSelected.value) {
        viewModel.itemNameSelected.value = ""
        viewModel.getItemsByType()
    }

    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.AddReview), color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { nav.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.Back),
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
            verticalArrangement = Arrangement.spacedBy(16.dp) // Increased spacing
        ) {
            Text(
                text = stringResource(id = R.string.ChooseItem),
                color = Color(0xFF03045E), // Dark blue text
                modifier = Modifier.padding(bottom = 8.dp)
            )

            ItemType(viewModel, ItemTypeList.entries)

            ItemName(viewModel, viewModel.itemsListByType.value)

            RatingDropDown(viewModel)

            OutlinedTextField(
                value = viewModel.reviewText.value,
                onValueChange = { viewModel.reviewText.value = it },
                label = { Text(text = stringResource(id = R.string.ReviewText)) },
                singleLine = false,
                minLines = 3,
                maxLines = 10,
                modifier = Modifier.fillMaxWidth(),
            )

            var pleaseSelectAnItem = stringResource(id = R.string.Pleaseselectanitem)
            var reviewTextCannotBeEmpty = stringResource(id = R.string.ReviewTextcannotbeempty)
            var reviewAddedSuccessfully = stringResource(id = R.string.ReviewAddedsuccessfully)
            var errorAddingReview = stringResource(id = R.string.Erroraddingreview)
            Button(
                onClick = {

                    if (viewModel.itemNameSelected.value.isEmpty() || viewModel.itemNameSelected.value.isBlank()) {

                        Toast.makeText(
                            context,
                            pleaseSelectAnItem,
                            Toast.LENGTH_LONG
                        ).show()
                    } else if (viewModel.reviewText.value.isEmpty() || viewModel.reviewText.value.isBlank()) {
                        Toast.makeText(
                            context,
                            reviewTextCannotBeEmpty,
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        val onSucces = {
                            Toast.makeText(
                                context,
                                reviewAddedSuccessfully,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        val onError = {
                            Toast.makeText(
                                context,
                                errorAddingReview,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        if (viewModel.loggedInUserFound.value) {
                            viewModel.findItemByTypeAndName() {
                                viewModel.addReview(onSucces, onError)
                            }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0077B6) // Medium blue button
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.AddReview), color = Color.White)
            }
        }
    }
}