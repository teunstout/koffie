package com.example.coffee.model.database_model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import kotlinx.android.parcel.Parcelize

/**
 * This is a coffee object
 */
@Parcelize
@Entity(primaryKeys = ["type", "date"], indices = [Index(value = ["date", "type"])])
data class Coffee(
    val type: String, // Coffee type(ex. Cappuccino).
    val date: String, // Date in string
    var amount: Int,  // Amount as integer
    val imgUrl: String    // Picture id as in resource id
): Parcelable