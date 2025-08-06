package com.example.reviewspot.features.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Review(
    @PrimaryKey(autoGenerate = true) val reviewID : Int,
    val reviewText : String,
    val rating : Int,
    val itemID : Int,
    val userID : Int
)
