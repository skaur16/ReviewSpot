package com.example.reviewspot.features.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class LoggedInUser(
    @PrimaryKey(autoGenerate = true) val loggedInUserID: Int,
    val userID: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)
