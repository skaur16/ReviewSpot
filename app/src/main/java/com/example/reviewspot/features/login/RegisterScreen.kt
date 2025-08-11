package com.example.reviewspot.features.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
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
            TopAppBar(
                title = {
                    Text(
                        text = "Register",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF023E8A)
                )
            )
        },
        containerColor = Color(0xFFCAF0F8)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = "Create an Account",
                color = Color(0xFF03045E),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            OutlinedTextField(
                value = viewModel.userFirstName.value,
                onValueChange = {
                    viewModel.userFirstName.value = it
                },
                label = { Text(text = "First Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                /*colors = TextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF0077B6),
                    unfocusedBorderColor = Color(0xFF00B4D8),
                    focusedLabelColor = Color(0xFF03045E),
                    unfocusedLabelColor = Color(0xFF023E8A),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )*/
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = viewModel.userLastName.value,
                onValueChange = {
                    viewModel.userLastName.value = it
                },
                label = { Text(text = "Last Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                /*colors = TextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF0077B6),
                    unfocusedBorderColor = Color(0xFF00B4D8),
                    focusedLabelColor = Color(0xFF03045E),
                    unfocusedLabelColor = Color(0xFF023E8A),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )*/
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = viewModel.userEmail.value,
                onValueChange = {
                    viewModel.userEmail.value = it
                },
                label = { Text(text = "Email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                /*colors = TextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF0077B6),
                    unfocusedBorderColor = Color(0xFF00B4D8),
                    focusedLabelColor = Color(0xFF03045E),
                    unfocusedLabelColor = Color(0xFF023E8A),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )*/
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = viewModel.userPassword.value,
                onValueChange = {
                    viewModel.userPassword.value = it
                },
                label = { Text(text = "Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                /*colors = TextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF0077B6),
                    unfocusedBorderColor = Color(0xFF00B4D8),
                    focusedLabelColor = Color(0xFF03045E),
                    unfocusedLabelColor = Color(0xFF023E8A),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )*/
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = viewModel.userConfirmPassword.value,
                onValueChange = {
                    viewModel.userConfirmPassword.value = it
                },
                label = { Text(text = "Confirm Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                /*colors = TextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF0077B6),
                    unfocusedBorderColor = Color(0xFF00B4D8),
                    focusedLabelColor = Color(0xFF03045E),
                    unfocusedLabelColor = Color(0xFF023E8A),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )*/
            )

            Spacer(modifier = Modifier.height(32.dp))

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
                            viewModel.getAllUsers(){
                                Log.d("Users", viewModel.registeredUsers.value.toString())
                            }
                        }
                    }
                    else{
                        Toast.makeText(
                            context,
                            viewModel.registerError.value,
                            Toast.LENGTH_SHORT
                        ).show()
                        viewModel.resetRegisterFields()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0077B6)
                )
            ){
                Text(text = "Register", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(){
                Text(
                    text = "Already have an account?",
                    color = Color(0xFF03045E)
                )
                Text(
                    text = " Login",
                    modifier = Modifier.clickable {
                        nav.navigate(AuthScreens.Login.name)
                    },
                    color = Color(0xFF0096C7),
                    fontWeight = FontWeight.Bold
                )

            }
        }
    }
}