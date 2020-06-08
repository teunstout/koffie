package com.example.coffee.data.database

import android.content.Context
import androidx.room.*
import com.example.coffee.model.database_model.Coffee
import com.example.coffee.model.database_model.CoffeeChoice

@Database(entities = [Coffee::class, CoffeeChoice::class], version = 2, exportSchema = false)
abstract class CoffeeDatabase : RoomDatabase() {
    abstract fun coffeeDao(): CoffeeDao

    companion object {
        private const val NAME_DATABASE = "Coffee" // Database name

        @Volatile // Changes direct visible to other threats
        private var INSTANCE: CoffeeDatabase? = null // Set a instance of database that is null

        fun getInstance(context: Context): CoffeeDatabase {
            if (INSTANCE == null) { // Check if there isn't any instance already
                // Create instance of database. Synchronized is used so all data changes are communicated directly to every function
                synchronized(CoffeeDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        CoffeeDatabase::class.java,
                        NAME_DATABASE
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE as CoffeeDatabase
        }
    }
}