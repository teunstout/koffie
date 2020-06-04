package com.example.coffee.model.databaseObjects

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(primaryKeys = ["type", "date"], indices = [Index(value = ["date", "type"])])
data class Coffee(
    val type: String,
    val date: String,
    var amount: Int,
    val imgId: Int
): Parcelable