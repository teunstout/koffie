package com.example.coffee.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.coffee.data.database.CoffeeRepository
import com.example.coffee.model.database_model.CoffeeChoice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class StartViewModel(application: Application) : AndroidViewModel(application) {
    private val coffeeRepository = CoffeeRepository(application.applicationContext)
    var coffeeChoices = coffeeRepository.getCoffeeChoices()

    suspend fun insertCoffeeChoice(coffeeChoice: CoffeeChoice) =
        withContext(Dispatchers.IO) { coffeeRepository.insertCoffeeChoice(coffeeChoice) }
}