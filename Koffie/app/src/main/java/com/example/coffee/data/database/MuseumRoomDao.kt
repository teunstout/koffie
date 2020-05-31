package com.example.coffee.data.database

import androidx.room.*
import com.example.coffee.model.CoffeeEntity

@Dao // Data access object
interface MuseumRoomDao {
    @Insert
    fun insertCoffee(coffee: CoffeeEntity)

    @Update
    fun updateCoffee(coffee: CoffeeEntity)

    @Delete
    fun deleteCoffee(coffee: CoffeeEntity)

    @Query("DELETE FROM CoffeeEntity WHERE date = :today")
    fun deleteCoffeeByDate(today: Long)

    @Query("SELECT * FROM coffeeEntity WHERE date = :today")
    fun getTodayCoffee(today: Long): List<CoffeeEntity>

//    @Query("SELECT type, SUM(amount) FROM coffeeEntity GROUP BY type")
//    fun getTotalCoffee(): List<CoffeeEntity>
}