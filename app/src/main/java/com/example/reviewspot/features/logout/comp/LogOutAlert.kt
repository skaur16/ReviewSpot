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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reviewspot.AuthScreens
import com.example.reviewspot.R
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
                        Toast.makeText(context, R.string.logged_out_success, Toast.LENGTH_SHORT).show()
                        nav.navigate(AuthScreens.Login.name)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.medium_blue) // Medium blue for the confirm button
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.log_out),
                    color = colorResource(id = R.color.white)
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
                    containerColor = colorResource(id = R.color.light_blue), // Lightest blue for the cancel button
                    contentColor = colorResource(id = R.color.deep_navy)// Dark text on light background
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
        },
        title = {
            Text(
                text = stringResource(id = R.string.logout_alert_title),
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.deep_navy) // Dark text for the title
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.logout_alert_message),
                color = colorResource(id = R.color.deep_navy) // Dark text for the body
            )
        },
        containerColor = colorResource(id = R.color.white), // White background for the dialog itself
        shape = RoundedCornerShape(12.dp)
    )
}