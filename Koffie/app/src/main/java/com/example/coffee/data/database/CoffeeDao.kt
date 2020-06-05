package com.example.coffee.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.coffee.model.databaseObjects.Coffee
import com.example.coffee.model.databaseObjects.CoffeeChoice

@Dao // Data access object
interface CoffeeDao {

    // Get all the coffee choices there are, displayed in viewpager
    @Query("SELECT * FROM CoffeeChoice")
    fun getAllCoffeeChoices(): LiveData<List<CoffeeChoice>>

    // Get coffee of today, Used to check if coffee of today already exists
    @Query("SELECT * FROM Coffee WHERE date = :dateString")
    fun getTodayCoffee(dateString: String): List<Coffee>

    // Get all the coffee as list, used to display coffee per day
    @Query("SELECT * FROM Coffee")
    fun getAllCoffee(): LiveData<List<Coffee>>

    // Get the total amount of coffee for each coffee you have
    @Query("SELECT type, date, SUM(amount) as amount, imgId FROM Coffee GROUP BY type")
    fun getTotalPerCoffee(): LiveData<List<Coffee>>

    // Track total amount of coffee, so we can display it and use it to display text
    @Query("SELECT SUM(amount) FROM Coffee WHERE date = :dateString")
    fun getTotalAllCoffee(dateString: String): LiveData<Int>

    // Insert a coffee choice
    @Insert
    suspend fun insertCoffeeChoice(coffeeChoice: CoffeeChoice)

    // Insert a coffee
    @Insert
    suspend fun saveCoffee(coffee: Coffee)

    // Update an already existing coffee
    @Update
    suspend fun updateCoffee(coffee: Coffee)

    @Delete
    suspend fun deleteCoffee(coffee: Coffee)
}