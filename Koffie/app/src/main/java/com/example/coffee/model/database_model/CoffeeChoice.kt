package com.example.coffee.model.database_model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Choices of coffee you can make
 */
@Entity
data class CoffeeChoice(
    val coffeeType: String, // Coffee type(ex. Cappuccino).
    val coffeeImgUrl: String, // Picture id as in resource id
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
)