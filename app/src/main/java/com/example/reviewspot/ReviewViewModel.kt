package com.example.reviewspot

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.reviewspot.features.addItem.comp.ItemTypeList
import com.example.reviewspot.features.room.Item
import com.example.reviewspot.features.room.LoggedInUser
import com.example.reviewspot.features.room.Review
import com.example.reviewspot.features.room.ReviewDatabase
import com.example.reviewspot.features.room.User
import kotlinx.coroutines.launch

class ReviewViewModel(application : Application) : AndroidViewModel(application) {




    //feature : room
    val db = ReviewApp.db.ReviewDao()

    //feature : addItem
    var itemName = mutableStateOf("")
    var itemTypeListExpanded = mutableStateOf(false)
    var itemTypeSelected = mutableStateOf(ItemTypeList.Movie)


    fun addItem(onSuccess : () -> Unit){
        viewModelScope.launch{
            val item = Item(
                itemID = 0,
                itemName = itemName.value,
                itemType = itemTypeSelected.value
            )
            db.insertItem(item)
        }.invokeOnCompletion {
            onSuccess()
        }
    }

    //feature : home
    var allItems = mutableStateOf<List<Item>>(listOf())

    fun getAllItems() {
        viewModelScope.launch {
            allItems.value = db.getAllItems()
        }
    }



    //feature : userLogin

    var userFirstName = mutableStateOf("")
    var userLastName = mutableStateOf("")
    var userEmail = mutableStateOf("")
    var userPassword = mutableStateOf("")
    var userConfirmPassword = mutableStateOf("")
    var registerError = mutableStateOf("")
    var registeredUsers = mutableStateOf<List<User>>(listOf())
    var userFound = mutableStateOf<User?>(null)
    var loggedInUser = mutableStateOf<LoggedInUser?>(null)
    var loggedInUserFound = mutableStateOf(false)

    fun validateUserInfo() : Boolean {
        val isValid = true
        if (userFirstName.value.isEmpty()) {
            registerError.value = "First Name is required"
            return false
        }
        if (userLastName.value.isEmpty()) {
            registerError.value += "Last Name is required"
            return false
        }
        if (userEmail.value.isEmpty() ) {
            registerError.value += "Email is required"
            return false
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail.value).matches()){
            registerError.value += "Invalid Email Format!"
            return false
        }
        if (userPassword.value.isEmpty()) {
            registerError.value += "Password is required"
            return false
        }
        if (userConfirmPassword.value.isEmpty()) {
            registerError.value += "Confirm Password is required"
        }
        if (userPassword.value != userConfirmPassword.value) {
            registerError.value += "Passwords do not match"
            return false
        }

        return isValid
    }

    fun saveUser(onSuccess: () -> Unit) {
        viewModelScope.launch{
            val user = User(
                userID = 0,
                firstName = userFirstName.value,
                lastName = userLastName.value,
                email = userEmail.value,
                password = userPassword.value
            )
            db.insertUser(user)
        }.invokeOnCompletion {
            onSuccess()
        }
    }

    fun resetRegisterFields() {
        registerError.value = ""
        userFirstName.value = ""
        userLastName.value = ""
        userEmail.value = ""
        userPassword.value = ""
        userConfirmPassword.value = ""
    }

    fun getAllUsers(onSuccess: () -> Unit){
        viewModelScope.launch {
            registeredUsers.value = db.getAllUsers()
        }.invokeOnCompletion {
            onSuccess()
        }

    }

    fun userFoundByEmailAndPassword() : Boolean {
        viewModelScope.launch{
            userFound.value = db.getUserByEmailAndPassword(userEmail.value, userPassword.value)
        }
        return userFound.value != null

    }

    fun saveLoggedInUser(onSuccess: () -> Unit) {
        viewModelScope.launch{
            val loggedInUser = LoggedInUser(
                loggedInUserID = 0,
                userID = userFound.value!!.userID,
                firstName = userFound.value!!.firstName,
                lastName = userFound.value!!.lastName,
                email = userFound.value!!.email,
                password = userFound.value!!.password
            )
            //saving loggedin user info
            db.insertLoggedInUser(loggedInUser)
        }.invokeOnCompletion {
            onSuccess()
        }
    }

    fun getLoggedInUser(){
        viewModelScope.launch {
            loggedInUser.value = db.getLoggedInUser()
        }.invokeOnCompletion {
            loggedInUserFound.value = loggedInUser.value != null
        }
    }

    //feature : Add Review

    var itemsListByType = mutableStateOf<List<Item>>(listOf())
    var itemsNameListExpanded = mutableStateOf(false)
    var itemNameSelected = mutableStateOf("")
    var ratingExpanded = mutableStateOf(false)
    var ratingSelected = mutableStateOf(0)
    var reviewText = mutableStateOf("")
    var itemFoundByNameAndType = mutableStateOf<Item?>(null)

    fun getItemsByType(){
        viewModelScope.launch {
            itemsListByType.value = db.getItemsByType(itemTypeSelected.value.name)
        }

    }

    fun findItemByTypeAndName() {
        viewModelScope.launch{
            itemFoundByNameAndType.value = db.getItemByTypeAndName(itemTypeSelected.value.name, itemNameSelected.value)
        }

    }

    fun addReview(onSuccess : () -> Unit){
        viewModelScope.launch{
            val itemId = itemFoundByNameAndType.value!!.itemID

            val review = Review(
                    reviewID = 0,
                    reviewText = reviewText.value,
                    rating = ratingSelected.value,
                    itemID = itemId,
                    userID = loggedInUser.value!!.userID
            )
            db.insertReview(review)

        }.invokeOnCompletion {
            onSuccess()
        }
    }

    //feature : MyReviews
    var myReviews = mutableStateOf<List<Review>>(listOf())

    fun getMyReviews(){
        viewModelScope.launch {
            myReviews.value = db.getReviewsByUser(loggedInUser.value!!.userID)
        }

    }

    var itemByID = mutableStateOf<Item?>(null)

    suspend fun getItemByID(id : Int) : Item? {
        return db.getItemByID(id)

    }
}