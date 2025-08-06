package com.example.reviewspot.features.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController
import com.example.reviewspot.AuthScreens
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.Screens

@Composable
fun LoginScreen(viewModel: ReviewViewModel, nav : NavController) {

    val context = LocalContext.current

    Scaffold(
        topBar = {
            Text(text = "Login")
        }
    ){
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            OutlinedTextField(
                value = viewModel.userEmail.value,
                onValueChange = {
                    viewModel.userEmail.value = it
                },
                label = { Text(text = "Email") },
                singleLine = true
            )

            OutlinedTextField(
                value = viewModel.userPassword.value,
                onValueChange = {
                    viewModel.userPassword.value = it
                },
                label = { Text(text = "Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )

            Button(
                onClick = {
                    if(viewModel.userFoundByEmailAndPassword()){
                        viewModel.saveLoggedInUser(){
                            Toast.makeText(
                                context,
                                "Login Successful!",
                                Toast.LENGTH_LONG
                            ).show()

                            nav.navigate(AuthScreens.Navigation.name)
                        }
                    }
                    else{
                        //show error
                        Toast.makeText(
                            context,
                            "No User found with these credentials!",
                            Toast.LENGTH_LONG
                        ).show()

                        //reset fields
                        viewModel.userEmail.value = ""
                        viewModel.userPassword.value = ""
                    }
                }
            ) {
                Text(text = "Login")
            }

            Row(){

                Text(text = "Don't have an account?")
                Text(text = "Register", modifier = Modifier.clickable {
                    nav.navigate(AuthScreens.Register.name)
                })
            }

        }
    }
}