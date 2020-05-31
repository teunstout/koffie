package com.example.coffee.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.coffee.model.CoffeeEntity

@Dao // Data access object
interface MuseumRoomDao {

    @Query("SELECT * FROM coffeeEntity WHERE date > :date")
    fun getCoffeeAfterDate(date: Long): LiveData<List<CoffeeEntity>>
    @Query("SELECT * FROM coffeeEntity")
    fun getCoffeeAfterDate(): LiveData<List<CoffeeEntity>>

    @Insert
    fun insertCoffee(coffee: CoffeeEntity)

    @Update
    fun updateCoffee(coffee: CoffeeEntity)

    @Delete
    fun deleteCoffee(coffee: CoffeeEntity)

    @Query("DELETE FROM CoffeeEntity WHERE date = :today")
    fun deleteCoffeeByDate(today: Long)

    @Query("SELECT * FROM coffeeEntity WHERE date = :today")
    fun getSpecificDayCoffee(today: Long): List<CoffeeEntity>

//
//
//    @Query("SELECT type, SUM(amount) FROM coffeeEntity GROUP BY type")
//    fun getTotalCoffee(): List<CoffeeEntity>
}