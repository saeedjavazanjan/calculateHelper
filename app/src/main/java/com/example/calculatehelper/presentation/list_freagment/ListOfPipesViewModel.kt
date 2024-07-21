package com.example.calculatehelper.presentation.list_freagment

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.calculatehelper.database.AppDatabase
import com.example.calculatehelper.database.Pipe
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListOfPipesViewModel
    @Inject constructor(
        private val sharedPreferences: SharedPreferences,
        private val db: AppDatabase

    )
    :ViewModel() {
    val list:List<Pipe> =db.dbDao().getAll()


}