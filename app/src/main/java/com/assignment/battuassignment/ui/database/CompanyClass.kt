package com.assignment.battuassignment.ui.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "company_table")
data class CompanyClass(

    @PrimaryKey
    var id : Int,

    @ColumnInfo(name = "name")
    var street : String,

    @ColumnInfo(name = "catchphrase")
    var catchPhrase : String,

    @ColumnInfo(name = "bs")
    var bs : String


    )