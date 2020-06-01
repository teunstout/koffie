package com.example.coffee.ui.addCoffee

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.coffee.data.database.MuseumRoomRepository
import com.example.coffee.model.databaseObjects.CoffeeChoice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddCoffeeViewModel(application: Application) : AndroidViewModel(application) {
    private val museumRoomRepository = MuseumRoomRepository(application.applicationContext)
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var coffeeChoices = ArrayList<CoffeeChoice>()

    fun getAllCoffeeChoices(): ArrayList<CoffeeChoice> {
        coroutineScope.launch {
            withContext(Dispatchers.IO)
            {
                coffeeChoices = withContext(Dispatchers.IO) {
                    museumRoomRepository.getCoffeeChoices() as ArrayList<CoffeeChoice>
                }
            }
        }
        return coffeeChoices
    }
}