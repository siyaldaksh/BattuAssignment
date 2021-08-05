package com.assignment.battuassignment.ui.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class DataClass (

    @PrimaryKey
    var id : Int = 1,

    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "username")
    var userName : String,

    @ColumnInfo(name = "email")
    var email : String,

    @ColumnInfo(name = "phone")
    var phone : String,

    @ColumnInfo(name = "website")
    var website : String
)
