package com.example.coffee.model.database_model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Choices of coffee you can make
 */
@Entity
data class CoffeeChoice(
    @PrimaryKey(autoGenerate = false)
    val coffeeType: String, // Coffee type(ex. Cappuccino).
    val coffeeImgId: Int // Picture id as in resource id

)