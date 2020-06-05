package com.example.coffee.data.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.coffee.model.database_model.Coffee
import com.example.coffee.model.database_model.CoffeeChoice


class CoffeeRepository(context: Context) {
    private var coffeeDatabase: CoffeeDatabase = CoffeeDatabase.getInstance(context) // Create a database
    private var coffeeDao: CoffeeDao = coffeeDatabase.coffeeDao() // Set the database interface to coffee interface

    // Suspend so actions can happen async
    suspend fun insertCoffeeChoice(coffeeChoice: CoffeeChoice) = coffeeDao.insertCoffeeChoice(coffeeChoice)

    suspend fun saveCoffee(coffee: Coffee) = coffeeDao.saveCoffee(coffee)

    suspend fun updateCoffee(coffee: Coffee) = coffeeDao.updateCoffee(coffee)

    suspend fun deleteCoffee(coffee: Coffee) = coffeeDao.deleteCoffee(coffee)

    // LiveData so we can observe the data
    fun getCoffeeChoices(): LiveData<List<CoffeeChoice>> = coffeeDao.getAllCoffeeChoices()

    fun getAllCoffee(): LiveData<List<Coffee>> = coffeeDao.getAllCoffee()

    fun getTotalPerCoffee(): LiveData<List<Coffee>> = coffeeDao.getTotalPerCoffee()

    fun getTotalAllCoffee(dateString: String): LiveData<Int> = coffeeDao.getTotalAllCoffee(dateString)

    fun getTodayCoffee(dateString: String): List<Coffee> = coffeeDao.getTodayCoffee(dateString)
}