package com.assignment.battuassignment.ui.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "location_table")
data class LocationClass(

    @PrimaryKey
    var id : Int = 1,

    @ColumnInfo(name = "lat")
    var latitude : Double = 0.0,

    @ColumnInfo(name = "long")
    var longitude : Double = 0.0
)