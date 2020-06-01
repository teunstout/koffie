package com.example.coffee.data.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.coffee.model.databaseObjects.Coffee
import com.example.coffee.model.databaseObjects.CoffeeChoice


class CoffeeRepository(context: Context) {
    private var coffeeDatabase: CoffeeDatabase = CoffeeDatabase.getInstance(context)
    private var coffeeDao: CoffeeDao = coffeeDatabase.coffeeDao()

    suspend fun insertCoffeeChoice(coffeeChoice: CoffeeChoice) = coffeeDao.insertCoffeeChoice(coffeeChoice)

    fun getCoffeeChoices(): LiveData<List<CoffeeChoice>> = coffeeDao.getAllCoffeeChoices()

    suspend fun saveCoffee(coffee: Coffee) = coffeeDao.saveCoffee(coffee)

    suspend fun updateCoffee(coffee: Coffee) = coffeeDao.updateCoffee(coffee)

    fun getTodayCoffee(dateString: String): List<Coffee> = coffeeDao.getTodayCoffee(dateString)
}