package com.example.coffee.ui.coffeeScreens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.coffee.data.database.MuseumRoomRepository
import java.time.LocalDate

class CoffeeViewModel(application: Application) : AndroidViewModel(application) {
    private val museumRoomRepository = MuseumRoomRepository(application.applicationContext)
    val amountOfCoffee = museumRoomRepository.getCoffeeAfterDate()


}