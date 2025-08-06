package com.example.reviewspot.features.logout.comp

import android.widget.Toast
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.reviewspot.AuthScreens
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.Screens

@Composable
fun LogOutAlert(
    viewModel : ReviewViewModel,
    nav : NavController
) {

    LaunchedEffect(key1 = viewModel.logOutAlertOpen.value) {

    }
    val context = LocalContext.current
   AlertDialog(
       onDismissRequest = {
           viewModel.logOutAlertOpen.value = false
       },
       confirmButton = {
           Button( onClick = {
               //delete loggedInUser
               viewModel.logOutAlertOpen.value = false

               viewModel.deleteLoggedInUser(){
                   Toast.makeText(context, "Logged Out Successfully!", Toast.LENGTH_SHORT).show()

                   nav.navigate(AuthScreens.Login.name)
               }
               //onSuccess - navigate to login screen


           }){
               Text(text = "Log Out!")
           }
       },
       dismissButton = {
           Button(onClick = {
               viewModel.logOutAlertOpen.value = false
               nav.navigate(Screens.Home.name)
           }) {
               Text(text = "Cancel")
           }
       },
       title = {
           Text(text = "Log Out Alert!")
       },
       text = {
           Text(text = "Are you sure you want to log out?")
       }
   )
}