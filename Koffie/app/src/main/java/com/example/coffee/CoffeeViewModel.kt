package com.example.coffee

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.coffee.data.database.CoffeeRepository
import com.example.coffee.model.databaseObjects.CoffeeChoice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
//import com.example.coffee.data.database.MuseumRoomRepository

class CoffeeViewModel(application: Application) : AndroidViewModel(application) {
    private val museumRoomRepository = CoffeeRepository(application.applicationContext)

    suspend fun insertCoffee(coffeeChoice: CoffeeChoice) =
        withContext(Dispatchers.IO) { museumRoomRepository.insertCoffeeChoice(coffeeChoice) }
}