package com.example.coffee.model.databaseObjects

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(primaryKeys = ["type", "date"])
data class Coffee(
    val type: String,
    val amount: Int,
    val date: Date
)