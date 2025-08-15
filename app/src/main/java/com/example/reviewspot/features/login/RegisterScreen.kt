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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reviewspot.AuthScreens
import com.example.reviewspot.R
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
                        text = stringResource(id = R.string.register),
                        color = colorResource(id = R.color.white)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.deep_navy)
                )
            )
        },
        containerColor = colorResource(id = R.color.lightest_blue)
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
                text = stringResource(id = R.string.create_account),
                color = colorResource(id = R.color.deep_navy),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            OutlinedTextField(
                value = viewModel.userFirstName.value,
                onValueChange = {
                    viewModel.userFirstName.value = it
                },
                label = { Text(text = stringResource(id = R.string.first_name)) },
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
                label = { Text(text = stringResource(id = R.string.last_name)) },
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
                label = { Text(text = stringResource(id = R.string.email)) },
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
                label = { Text(text = stringResource(id = R.string.password)) },
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
                label = { Text(text = stringResource(id = R.string.confirm_password)) },
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
                                R.string.user_registered,
                                Toast.LENGTH_SHORT
                            ).show()

                            nav.navigate(AuthScreens.Login.name)
                            viewModel.resetRegisterFields()
                            viewModel.getAllUsers(){
                                //Log.d("Users", viewModel.registeredUsers.value.toString())
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
                    containerColor = colorResource(id = R.color.medium_blue)
                )
            ){
                Text(text = stringResource(id = R.string.register), color = colorResource(id = R.color.white))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(){
                Text(
                    text = stringResource(id = R.string.already_have_account),
                    color = colorResource(id = R.color.deep_navy)
                )
                Text(
                    text = stringResource(id = R.string.login),
                    modifier = Modifier.clickable {
                        nav.navigate(AuthScreens.Login.name)
                    },
                    color = colorResource(id = R.color.accent_blue),
                    fontWeight = FontWeight.Bold
                )

            }
        }
    }
}