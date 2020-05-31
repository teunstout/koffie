package com.example.coffee.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(primaryKeys = ["type", "date"])
data class CoffeeEntity(
    val type: String,
    val amount: Int,
    val date: Date
)