package com.example.reviewspot.features.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Review::class, Item::class, User::class, LoggedInUser::class], version = 5)
abstract class ReviewDatabase : RoomDatabase() {
    abstract fun ReviewDao() : ReviewDao
}