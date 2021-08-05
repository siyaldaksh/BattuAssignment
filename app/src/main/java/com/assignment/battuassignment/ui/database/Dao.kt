package com.assignment.battuassignment.ui.database

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface Dao {

    @Insert
    fun insertUser(userData: DataClass)

    @Insert
    fun insertAddress(addressClass: AddressClass)

    @Insert
    fun insertLocation(locationClass: LocationClass)

    @Insert
    fun insertCompany(companyClass: CompanyClass)



}