package com.example.coffee

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.coffee.data.database.MuseumRoomRepository
import com.example.coffee.model.databaseObjects.CoffeeChoice
//import com.example.coffee.data.database.MuseumRoomRepository
import java.time.LocalDate

class CoffeeViewModel(application: Application) : AndroidViewModel(application) {
    private val museumRoomRepository = MuseumRoomRepository(application.applicationContext)

    suspend fun insertCoffee(coffeeChoice: CoffeeChoice) = museumRoomRepository.insertCoffeeChoice(coffeeChoice)
    suspend fun getAllCoffee() = museumRoomRepository.getCoffeeChoices()
}