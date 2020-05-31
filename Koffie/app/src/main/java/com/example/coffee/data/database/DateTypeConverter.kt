package com.example.coffee.data.database

import androidx.room.TypeConverter
import java.util.*

class DateTypeConverter {
    @TypeConverter
    fun fromDateToLong(date: Date?): Long?{
        return date?.time
    }

    @TypeConverter
    fun fromLongToDate(value: Long?): Date? {
        return if (value == null) null else Date(value)
     }

}