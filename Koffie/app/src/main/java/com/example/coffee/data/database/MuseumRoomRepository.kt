package com.example.coffee.data.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.coffee.model.CoffeeEntity


class MuseumRoomRepository(context: Context) {
    private var museumRoomDatabase: MuseumRoomDatabase = MuseumRoomDatabase.getInstance(context)
    private var museumRoomDao: MuseumRoomDao = museumRoomDatabase.museumRoomDao()

    fun getCoffeeAfterDate(): LiveData<List<CoffeeEntity>> = museumRoomDao.getCoffeeAfterDate()

//    fun getTotalCoffee() = museumRoomDao.getTotalCoffee()
}