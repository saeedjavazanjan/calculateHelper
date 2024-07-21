package com.example.calculatehelper.presentation.main

import android.content.res.Configuration
import android.os.Build
import android.view.ContextThemeWrapper
import androidx.lifecycle.ViewModel
import com.example.calculatehelper.database.AppDatabase
import com.example.calculatehelper.database.DatabaseHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject
@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        private val db:AppDatabase
    )

    :ViewModel() {

    //TODO inject from hilt
    val databaseHelper = DatabaseHelper(db)


    fun deleteDatabase():Boolean{
        try {
            databaseHelper.delete()
            return true
        }catch (_:Exception){
            return false
        }

    }

    }