package com.example.coffee.data.database

import android.content.Context
import androidx.room.*
import com.example.coffee.model.databaseObjects.Coffee
import com.example.coffee.model.databaseObjects.CoffeeChoice

@Database(entities = [Coffee::class, CoffeeChoice::class], version = 1, exportSchema = false)
abstract class CoffeeDatabase : RoomDatabase() {
    abstract fun coffeeDao(): CoffeeDao

    companion object {
        private const val NAME_DATABASE = "Coffee"

        @Volatile // Changes direct visible to other threats
        private var INSTANCE: CoffeeDatabase? = null

        fun getInstance(context: Context): CoffeeDatabase {
            if (INSTANCE == null) { // Look if there is a instance
                synchronized(CoffeeDatabase::class.java) { // Create instance of database
                    INSTANCE = Room.databaseBuilder(
                        context,
                        CoffeeDatabase::class.java,
                        NAME_DATABASE
                    )
                        .build()
                }
            }

            return INSTANCE as CoffeeDatabase
        }
    }
}