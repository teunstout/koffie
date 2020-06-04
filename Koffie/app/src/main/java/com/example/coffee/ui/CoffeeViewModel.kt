package com.example.coffee.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.coffee.data.database.CoffeeRepository
import com.example.coffee.ui.CoffeeActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CoffeeViewModel(application: Application): AndroidViewModel(application){
    private val coffeeRepository = CoffeeRepository(application.applicationContext)
    var coffee = coffeeRepository.getAllCoffee()
    var totalPerCoffee = coffeeRepository.getTotalPerCoffee()
    var totalAllCoffeeInt = coffeeRepository.getTotalAllCoffee(CoffeeActivity.today())
}