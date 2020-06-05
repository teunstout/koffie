package com.example.coffee.ui.fragments.coffee_fragment.update_coffee_activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.coffee.data.database.CoffeeRepository
import com.example.coffee.model.database_model.Coffee
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateCoffeeViewModel(application: Application) : AndroidViewModel(application) {
    private val coffeeRepository = CoffeeRepository(application.applicationContext)

    fun insertCoffee(coffee: Coffee) {
        CoroutineScope(Dispatchers.Default).launch {
            CoroutineScope(Dispatchers.IO).launch {
                coffeeRepository.updateCoffee(coffee)
            }
        }
    }

    fun deleteCoffee(coffee: Coffee) {
        CoroutineScope(Dispatchers.Default).launch {
            CoroutineScope(Dispatchers.IO).launch {
                coffeeRepository.deleteCoffee(coffee)
            }
        }
    }
}