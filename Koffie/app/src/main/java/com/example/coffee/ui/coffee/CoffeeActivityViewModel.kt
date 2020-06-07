package com.example.coffee.ui.coffee

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.coffee.data.database.CoffeeRepository

class CoffeeActivityViewModel(application: Application): AndroidViewModel(application){
    private val coffeeRepository = CoffeeRepository(application.applicationContext)

    // All coffee.
    var coffee = coffeeRepository.getAllCoffee()

    // For each coffee the total of cups.
    var totalPerCoffee = coffeeRepository.getTotalPerCoffee()

    // Total all coffee drank today as int for smileys.
    // We can show status that way.
    var totalAllCoffeeInt = coffeeRepository.getTotalAllCoffee(CoffeeActivity.today())
}