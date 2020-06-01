package com.example.coffee.ui.addCoffee

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.coffee.CoffeeActivity
import com.example.coffee.data.database.CoffeeRepository
import com.example.coffee.model.databaseObjects.Coffee
import com.example.coffee.model.databaseObjects.CoffeeChoice
import kotlinx.coroutines.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AddCoffeeViewModel(application: Application) : AndroidViewModel(application) {
    private val coffeeRepository = CoffeeRepository(application.applicationContext)
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val format = DateTimeFormatter.ofPattern(CoffeeActivity.DATE_STRING)

    var choices = coffeeRepository.getCoffeeChoices()

    fun saveCoffee(coffeeChoice: CoffeeChoice, amount: String) {
        val coffeeToSave = Coffee(coffeeChoice.coffeeName, LocalDate.now().format(format), amount.toInt()) // Coffee object that needs to be added
        val allTodayCoffee: List<Coffee>? = coffeeRepository.getTodayCoffee(LocalDate.now().format(format)) // All coffee that is in the database from today
            .filter{ it.type == coffeeToSave.type} // Filter to look if there is a coffee

        if (allTodayCoffee.isNullOrEmpty()) {
            Log.i("SaveCoffee", amount)
            CoroutineScope(Dispatchers.IO).launch {
                coffeeRepository.saveCoffee(coffeeToSave)
            }
        } else {
            allTodayCoffee[allTodayCoffee.size].amount += amount.toInt()
            Log.i("UpdateCoffee", allTodayCoffee[allTodayCoffee.size].amount.toString())
            CoroutineScope(Dispatchers.IO).launch {
                coffeeRepository.updateCoffee(allTodayCoffee[allTodayCoffee.size])
            }
        }
    }

}