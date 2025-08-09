package com.example.reviewspot.features.AddReview

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.features.AddReview.comp.ItemName
import com.example.reviewspot.features.AddReview.comp.RatingDropDown
import com.example.reviewspot.features.addItem.comp.ItemType
import com.example.reviewspot.features.addItem.comp.ItemTypeList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReviewScreen(navController: NavController, viewModel: ReviewViewModel) {

    LaunchedEffect(key1 = viewModel.itemTypeSelected.value) {
        viewModel.itemNameSelected.value = ""
        viewModel.getItemsByType()

    }

    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Add Review") }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Choose Item : ")

            ItemType(viewModel, ItemTypeList.entries)

            ItemName(viewModel, viewModel.itemsListByType.value)

            RatingDropDown(viewModel)

            OutlinedTextField(
                value = viewModel.reviewText.value,
                onValueChange = { viewModel.reviewText.value = it },
                label = { Text(text = "Review Text : ") },
                singleLine = false,
                minLines = 3,
                maxLines = 10
            )

            Button(
                onClick = {
                    //add review to database
                    if(viewModel.itemNameSelected.value.isEmpty() || viewModel.itemNameSelected.value.isBlank()){
                    Toast.makeText(
                        context,
                        "Please select an item!",
                        Toast.LENGTH_LONG
                    ).show()
                    }
                    else if(viewModel.reviewText.value.isEmpty() || viewModel.reviewText.value.isBlank()){
                        Toast.makeText(
                            context,
                            "Review Text cannot be empty!",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    else{
                        val onSucces = {
                            Toast.makeText(
                                context,
                                "Review Added successfully!",
                                Toast.LENGTH_LONG
                            ).show()

                        }
                        val onError = {
                            Toast.makeText(
                                context,
                                "Error adding review!",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                        //reaching here means all conditions are satisfied and user has entered all the values correct

                        if(viewModel.loggedInUserFound.value){
                            viewModel.findItemByTypeAndName(){
                                viewModel.addReview(onSucces, onError)
                            }
                        }

                    }
                }
            ) {
                Text(text = "Add Review")
            }


        }
    }
}

