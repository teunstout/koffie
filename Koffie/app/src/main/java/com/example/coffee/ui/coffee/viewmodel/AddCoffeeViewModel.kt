package com.example.coffee.ui.coffee.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.coffee.data.database.CoffeeRepository
import com.example.coffee.model.database_model.Coffee
import com.example.coffee.model.database_model.CoffeeChoice
import com.example.coffee.ui.coffee.CoffeeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddCoffeeViewModel(application: Application) : AndroidViewModel(application) {
    private val coffeeRepository = CoffeeRepository(application.applicationContext)
    private val startArray = 0 // Always 0 because the primary key of coffee
    var choices = coffeeRepository.getCoffeeChoices() // Choices of coffee

    // Save coffee
    fun saveCoffee(coffeeToSave: Coffee) {
        CoroutineScope(Dispatchers.Main).launch {
            // Filter the coffee of today to type.
            // If someone already had a cup of this coffee this will be that coffee object.
            val coffeeToday = withContext(Dispatchers.IO) {
                coffeeRepository.getTodayCoffee(CoffeeActivity.today())
                    .filter { it.type == coffeeToSave.type } // Filter to look if there is a coffee
            }

            // If coffee is not consumed today add it to the database
            // else add the amount of coffee from now and add it to the other cups and update database.
            if (coffeeToday.isNullOrEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {
                    coffeeRepository.saveCoffee(coffeeToSave)
                }
            } else {
                coffeeToday[startArray].amount += coffeeToSave.amount
                CoroutineScope(Dispatchers.IO).launch {
                    coffeeRepository.updateCoffee(coffeeToday[startArray])
                }
            }
        }
    }
}