package com.assignment.battuassignment.ui.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataClass::class,AddressClass::class,LocationClass::class,CompanyClass::class],version = 1,exportSchema = false)
abstract class MyDatabase : RoomDatabase(){

    abstract val dao: Dao

    companion object {

        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}