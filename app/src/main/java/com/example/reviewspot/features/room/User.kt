package com.example.reviewspot.features.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val userID : Int,
    val firstName : String,
    val lastName : String,
    val email : String,
    val password : String
)
