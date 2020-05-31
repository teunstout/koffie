package com.example.coffee.data.database

import android.content.Context
import androidx.room.*
import com.example.coffee.model.CoffeeEntity

@Database(entities = [CoffeeEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateTypeConverter::class)
abstract class MuseumRoomDatabase : RoomDatabase() {
    abstract fun museumRoomDao(): MuseumRoomDao

    companion object {
        private const val NAME_DATABASE = "Coffee"

        @Volatile // Changes direct visible to other threats
        private var INSTANCE: MuseumRoomDatabase? = null

        fun getInstance(context: Context): MuseumRoomDatabase {
            if (INSTANCE == null) { // Look if there is a instance
                synchronized(MuseumRoomDatabase::class.java) { // Create instance of database
                    INSTANCE = Room.databaseBuilder(
                        context,
                        MuseumRoomDatabase::class.java,
                        NAME_DATABASE
                    )
                        .build()
                }
            }

            return INSTANCE as MuseumRoomDatabase
        }
    }
}