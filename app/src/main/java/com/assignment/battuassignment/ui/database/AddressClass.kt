package com.assignment.battuassignment.ui.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "address_table")
data class AddressClass(

    @PrimaryKey
    var id : Int,

    @ColumnInfo(name = "street")
    var street : String,

    @ColumnInfo(name = "suite")
    var suite : String,

    @ColumnInfo(name = "city")
    var city : String,

    @ColumnInfo(name = "zipcode")
    var zipcode : String
)