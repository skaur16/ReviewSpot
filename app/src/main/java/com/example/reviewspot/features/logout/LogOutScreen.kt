package com.example.reviewspot.features.logout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.features.logout.comp.LogOutAlert

@Composable
fun LogOutScreen(
    nav : NavController,
    viewModel: ReviewViewModel
) {
   Scaffold(
       topBar = {
           Text(text = "Log Out")
       }
   ) {
       Column(
           modifier = Modifier.fillMaxSize().padding(it),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center
       ) {
            LogOutAlert(viewModel, nav)
       }
   }
}