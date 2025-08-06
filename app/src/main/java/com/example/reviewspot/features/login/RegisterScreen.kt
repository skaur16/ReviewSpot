package com.example.reviewspot.features.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController
import com.example.reviewspot.AuthScreens
import com.example.reviewspot.ReviewViewModel
import com.example.reviewspot.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(viewModel: ReviewViewModel, nav : NavController) {

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = {Text(text = "Register")})
        }
    ){
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            OutlinedTextField(
                value = viewModel.userFirstName.value,
                onValueChange = {
                    viewModel.userFirstName.value = it
                },
                label = { Text(text = "First Name") },
                singleLine = true
            )

            OutlinedTextField(
                value = viewModel.userLastName.value,
                onValueChange = {
                    viewModel.userLastName.value = it
                },
                label = { Text(text = "Last Name") },
                singleLine = true
            )

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

            OutlinedTextField(
                value = viewModel.userConfirmPassword.value,
                onValueChange = {
                    viewModel.userConfirmPassword.value = it
                },
                label = { Text(text = "Confirm Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )

            Button(
                onClick = {
                  if(viewModel.validateUserInfo()){
                      viewModel.saveUser(){
                          Toast.makeText(
                              context,
                              "User Registered Successfully!",
                              Toast.LENGTH_SHORT
                          ).show()

                          nav.navigate(AuthScreens.Login.name)
                          viewModel.resetRegisterFields()
                          //save check
                          viewModel.getAllUsers(){
                              Log.d("Users", viewModel.registeredUsers.value.toString())
                          }
                      }
                  }
                    else{
                        //show error = toast + reset
                        Toast.makeText(
                            context,
                            viewModel.registerError.value,
                            Toast.LENGTH_SHORT
                        ).show()

                        viewModel.resetRegisterFields()

                  }
                }
            ){
                Text(text = "Register")
            }
            Row(){
                Text(text = "Already have an account?")
                Text(text = "Login", modifier = Modifier.clickable {
                    nav.navigate(AuthScreens.Login.name)
                })

            }
        }
    }
}