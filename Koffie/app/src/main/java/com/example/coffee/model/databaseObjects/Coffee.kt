package com.example.coffee.model.databaseObjects

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(primaryKeys = ["type", "date"])
data class Coffee(
    val type: String,
    val date: String,
    var amount: Int,
    val imgId: Int
)