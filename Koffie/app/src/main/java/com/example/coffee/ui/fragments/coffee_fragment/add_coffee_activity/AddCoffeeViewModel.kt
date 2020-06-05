package com.example.coffee.ui.fragments.coffee_fragment.add_coffee_activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.coffee.data.database.CoffeeRepository
import com.example.coffee.model.database_model.Coffee
import com.example.coffee.model.database_model.CoffeeChoice
import com.example.coffee.ui.CoffeeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddCoffeeViewModel(application: Application) : AndroidViewModel(application) {
    private val coffeeRepository = CoffeeRepository(application.applicationContext)
    var choices = coffeeRepository.getCoffeeChoices()

    fun saveCoffee(coffeeChoice: CoffeeChoice, amount: String) {
        val todayDate = CoffeeActivity.today()
        // Coffee object that needs to be added
        val coffeeToSave = Coffee(
            coffeeChoice.coffeeType,
            todayDate,
            amount.toInt(),
            coffeeChoice.coffeeImgId
        )

        CoroutineScope(Dispatchers.Default).launch {
            val allTodayCoffee = withContext(Dispatchers.IO) {
                coffeeRepository.getTodayCoffee(todayDate)
                    .filter { it.type == coffeeToSave.type } // Filter to look if there is a coffee
            }

            if (allTodayCoffee.isNullOrEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {
                    coffeeRepository.saveCoffee(coffeeToSave)
                }
            } else {
                allTodayCoffee[0].amount += amount.toInt()
                CoroutineScope(Dispatchers.IO).launch {
                    coffeeRepository.updateCoffee(allTodayCoffee[0])
                }
            }
        }
    }
}