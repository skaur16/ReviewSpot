package com.example.reviewspot.features.logout.comp

import android.widget.Toast
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reviewspot.AuthScreens
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.Screens

@Composable
fun LogOutAlert(
    viewModel: ReviewViewModel,
    nav: NavController
) {

    LaunchedEffect(key1 = viewModel.logOutAlertOpen.value) {
        // You can use this for any side effects when the dialog opens/closes
    }
    val context = LocalContext.current
    AlertDialog(
        onDismissRequest = {
            viewModel.logOutAlertOpen.value = false
        },
        confirmButton = {
            Button(
                onClick = {
                    viewModel.logOutAlertOpen.value = false
                    viewModel.deleteLoggedInUser() {
                        Toast.makeText(context, "Logged Out Successfully!", Toast.LENGTH_SHORT).show()
                        nav.navigate(AuthScreens.Login.name)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0077B6) // Medium blue for the confirm button
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Log Out!",
                    color = Color.White
                )
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    viewModel.logOutAlertOpen.value = false
                    nav.navigate(AuthScreens.Navigation.name)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFCAF0F8), // Lightest blue for the cancel button
                    contentColor = Color(0xFF03045E) // Dark text on light background
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Cancel")
            }
        },
        title = {
            Text(
                text = "Log Out Alert!",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF03045E) // Dark text for the title
            )
        },
        text = {
            Text(
                text = "Are you sure you want to log out?",
                color = Color(0xFF03045E) // Dark text for the body
            )
        },
        containerColor = Color.White, // White background for the dialog itself
        shape = RoundedCornerShape(12.dp)
    )
}