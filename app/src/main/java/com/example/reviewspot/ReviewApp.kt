package com.example.reviewspot

import android.app.Application
import androidx.room.Room
import com.example.reviewspot.features.room.ReviewDatabase

class ReviewApp : Application() {

companion object{
    lateinit var db : ReviewDatabase
    private set
}

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            ReviewDatabase::class.java,
            "ReviewDatabase"
        ).fallbackToDestructiveMigration()
            .build()
    }
}