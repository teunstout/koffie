package com.example.coffee.data.database

import androidx.room.*
import com.example.coffee.model.databaseObjects.CoffeeChoice

@Dao // Data access object
interface MuseumRoomDao {
    // CoffeeChoice table
    @Insert
    suspend fun insertCoffeeChoice(coffeeChoice: CoffeeChoice)

    @Query("SELECT * FROM CoffeeChoice")
    suspend fun getAllCoffeeChoices(): List<CoffeeChoice>

    // Coffee table
}