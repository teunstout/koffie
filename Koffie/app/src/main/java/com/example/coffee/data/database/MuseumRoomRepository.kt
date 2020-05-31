package com.example.coffee.data.database

import android.content.Context
import com.example.coffee.model.CoffeeEntity


class MuseumRoomRepository(context: Context) {
    private var museumRoomDatabase: MuseumRoomDatabase = MuseumRoomDatabase.getInstance(context)
    private var museumRoomDao: MuseumRoomDao = museumRoomDatabase.museumRoomDao()


    fun insertCoffee(coffee: CoffeeEntity) =  museumRoomDao.insertCoffee(coffee)

    fun updateCoffee(coffee: CoffeeEntity) = museumRoomDao.updateCoffee(coffee)

    fun deleteCoffee(coffee: CoffeeEntity) = museumRoomDao.deleteCoffee(coffee)

    fun deleteCoffeeByDate(today: Long) = museumRoomDao.deleteCoffeeByDate(today)

    fun getTodayCoffee(today: Long) = museumRoomDao.getTodayCoffee(today)

//    fun getTotalCoffee() = museumRoomDao.getTotalCoffee()
}