package com.example.reviewspot.features.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.reviewspot.features.addItem.comp.ItemTypeList

@Entity
data class Item(
    @PrimaryKey(autoGenerate = true) val itemID : Int,
    val itemName : String,
    val itemType : ItemTypeList,
    val itemImage : Int
)
