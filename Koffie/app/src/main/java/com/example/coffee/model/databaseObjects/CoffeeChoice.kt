package com.example.coffee.model.databaseObjects

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CoffeeChoice(
    val coffeeImgInt: Int,
    val coffeeName: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null
)