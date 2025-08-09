package com.example.reviewspot

import android.app.Application
import androidx.room.Room
import com.example.reviewspot.ReviewApp.Companion.db
import com.example.reviewspot.features.addItem.comp.ItemTypeList
import com.example.reviewspot.features.room.Item
import com.example.reviewspot.features.room.Review
import com.example.reviewspot.features.room.ReviewDatabase
import com.example.reviewspot.features.room.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

        seedDatabaseIfEmpty()
    }
}

private fun seedDatabaseIfEmpty() {
    CoroutineScope(Dispatchers.IO).launch {
        val dao = db.ReviewDao()
        val itemCount = dao.getItemCount()

        if (itemCount == 0) {
            // Seed users
            val users = listOf(
                User(0, "Alice", "Smith", "alice@example.com", "pass1"),
                User(0, "Bob", "Jones", "bob@example.com", "pass2"),
                User(0, "Charlie", "Brown", "charlie@example.com", "pass3"),
                User(0, "Diana", "Prince", "diana@example.com", "pass4"),
            )

            val userIds = mutableListOf<Int>()
            for (user in users) {
                val id = dao.insertUser(user)  // Assume insertUser returns Long for inserted id
                userIds.add(id.toInt())
            }

            // Seed items
            val items = listOf(
                Item(0, "Inception", ItemTypeList.Movie, R.drawable.movie),
                Item(0, "The Hobbit", ItemTypeList.Book, R.drawable.book),
                Item(0, "Cyberpunk 2077", ItemTypeList.Game, R.drawable.game),
                Item(0, "Kotlin for Beginners", ItemTypeList.Course, R.drawable.course),
                Item(0, "Interstellar", ItemTypeList.Movie, R.drawable.movie),
                Item(0, "1984", ItemTypeList.Book, R.drawable.book),
                Item(0, "The Witcher 3", ItemTypeList.Game, R.drawable.game),
                Item(0, "Android Development", ItemTypeList.Course, R.drawable.course),
                Item(0, "The Matrix", ItemTypeList.Movie, R.drawable.movie),
                Item(0, "Clean Code", ItemTypeList.Book, R.drawable.book)
            )

            val itemIds = mutableListOf<Int>()
            for (item in items) {
                val id = dao.insertItem(item)
                itemIds.add(id.toInt())
            }

            // Seed 2 reviews per item by different users
            val now = System.currentTimeMillis()
            var reviewIdCounter = 1
            for ((index, itemId) in itemIds.withIndex()) {
                val user1 = userIds[index % userIds.size]
                val user2 = userIds[(index + 1) % userIds.size]

                val review1 = Review(
                    reviewID = 0,
                    reviewText = "Review 1 for item $itemId",
                    rating = 4,
                    itemID = itemId,
                    userID = user1,
                    timestamp = now - (index * 100000L)
                )

                val review2 = Review(
                    reviewID = 0,
                    reviewText = "Review 2 for item $itemId",
                    rating = 5,
                    itemID = itemId,
                    userID = user2,
                    timestamp = now - (index * 50000L)
                )

                dao.insertReview(review1)
                dao.insertReview(review2)
            }
        }
    }
}