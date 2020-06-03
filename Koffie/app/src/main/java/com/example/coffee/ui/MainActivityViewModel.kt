package com.example.coffee.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.coffee.data.database.CoffeeRepository
import com.example.coffee.model.databaseObjects.CoffeeChoice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
//import com.example.coffee.data.database.MuseumRoomRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val coffeeRepository = CoffeeRepository(application.applicationContext)

    suspend fun insertCoffeeChoice(coffeeChoice: CoffeeChoice) =
        withContext(Dispatchers.IO) { coffeeRepository.insertCoffeeChoice(coffeeChoice) }
}