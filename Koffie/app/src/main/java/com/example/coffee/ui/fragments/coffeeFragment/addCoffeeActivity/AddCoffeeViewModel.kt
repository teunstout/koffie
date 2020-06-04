package com.example.coffee.ui.fragments.coffeeFragment.addCoffeeActivity

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.coffee.ui.CoffeeActivity
import com.example.coffee.data.database.CoffeeRepository
import com.example.coffee.model.databaseObjects.Coffee
import com.example.coffee.model.databaseObjects.CoffeeChoice
import kotlinx.coroutines.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AddCoffeeViewModel(application: Application) : AndroidViewModel(application) {
    private val coffeeRepository = CoffeeRepository(application.applicationContext)
    var choices = coffeeRepository.getCoffeeChoices()

    fun saveCoffee(coffeeChoice: CoffeeChoice, amount: String) {
        val todayDate = CoffeeActivity.today()
        // Coffee object that needs to be added
        val coffeeToSave = Coffee(
            coffeeChoice.coffeeName,
            todayDate,
            amount.toInt(),
            coffeeChoice.coffeeImgInt
        )

        CoroutineScope(Dispatchers.Default).launch {
            val allTodayCoffee = withContext(Dispatchers.IO) {
                coffeeRepository.getTodayCoffee(todayDate)
                    .filter { it.type == coffeeToSave.type } // Filter to look if there is a coffee
            }

            if (allTodayCoffee.isNullOrEmpty()) {
                Log.i("SaveCoffee", amount)
                CoroutineScope(Dispatchers.IO).launch {
                    coffeeRepository.saveCoffee(coffeeToSave)
                }
            } else {
                allTodayCoffee[0].amount += amount.toInt()
                Log.i("UpdateCoffee", allTodayCoffee[0].amount.toString())
                CoroutineScope(Dispatchers.IO).launch {
                    coffeeRepository.updateCoffee(allTodayCoffee[0])
                }
            }
        }
    }
}