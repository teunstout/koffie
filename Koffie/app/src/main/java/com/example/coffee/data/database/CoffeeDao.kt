package com.example.coffee.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.coffee.model.databaseObjects.Coffee
import com.example.coffee.model.databaseObjects.CoffeeChoice

@Dao // Data access object
interface CoffeeDao {

    // CoffeeChoice table
    @Query("SELECT * FROM CoffeeChoice")
    fun getAllCoffeeChoices(): LiveData<List<CoffeeChoice>>

    @Insert
    suspend fun insertCoffeeChoice(coffeeChoice: CoffeeChoice)


    // Coffee table
    @Query("SELECT * FROM Coffee WHERE date = :dateString")
    fun getTodayCoffee(dateString: String): List<Coffee>

    @Insert
    suspend fun saveCoffee(coffee: Coffee)

    @Update
    suspend fun updateCoffee(coffee: Coffee)


}