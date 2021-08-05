package com.assignment.battuassignment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assignment.battuassignment.ui.database.Dao

class WelcomeViewModelFactory(private val dataSource: Dao,
                              private val application: Application
) : ViewModelProvider.Factory
{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)) {
            return WelcomeViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}