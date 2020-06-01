package com.example.coffee.data.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.coffee.model.databaseObjects.Coffee
import com.example.coffee.model.databaseObjects.CoffeeChoice


class MuseumRoomRepository(context: Context) {
    private var museumRoomDatabase: MuseumRoomDatabase = MuseumRoomDatabase.getInstance(context)
    private var museumRoomDao: MuseumRoomDao = museumRoomDatabase.museumRoomDao()

    suspend fun insertCoffeeChoice(coffeeChoice: CoffeeChoice) = museumRoomDao.insertCoffeeChoice(coffeeChoice)

    suspend fun getCoffeeChoices(): List<CoffeeChoice> = museumRoomDao.getAllCoffeeChoices()
}