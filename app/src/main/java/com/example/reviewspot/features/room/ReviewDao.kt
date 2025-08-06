package com.example.reviewspot.features.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ReviewDao {

    @Insert
    suspend fun insertReview(review: Review)

    @Query("SELECT * FROM Review WHERE itemID = :itemID")
    suspend fun getReviewsForItem(itemID: Int): List<Review>

    @Query("SELECT * FROM Review WHERE userID = :userID")
    suspend fun getReviewsByUser(userID: Int): List<Review>

    @Query("SELECT * FROM Item")
    suspend fun getAllItems(): List<Item>

    @Insert
    suspend fun insertItem(item: Item)

    @Query("SELECT * FROM Item WHERE itemID = :itemID")
    suspend fun getItemByID(itemID: Int): Item?

    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM User")
    suspend fun getAllUsers() : List<User>

    @Query("SELECT * FROM User WHERE email = :email AND password = :password")
    suspend fun getUserByEmailAndPassword(email: String, password: String): User?

    @Insert
    suspend fun insertLoggedInUser(loggedInUser: LoggedInUser)

    @Query("SELECT * FROM LoggedInUser")
    suspend fun getLoggedInUser() : LoggedInUser?

    @Query("SELECT * FROM Item where itemType = :itemType")
    suspend fun getItemsByType(itemType : String) : List<Item>

    @Query("SELECT * from Item where itemType = :itemType and itemName = :itemName")
    suspend fun getItemByTypeAndName(itemType : String, itemName : String) : Item



}