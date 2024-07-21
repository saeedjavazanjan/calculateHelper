package com.example.calculatehelper.presentation.calculate

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.calculatehelper.database.AppDatabase
import com.example.calculatehelper.database.DatabaseHelper
import com.example.calculatehelper.database.Pipe
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.math.BigInteger
import javax.inject.Inject
import kotlin.math.atan
import kotlin.math.pow

@HiltViewModel
class CalculateViewModel
@Inject
constructor(
    private val sharedPreferences: SharedPreferences,
    private val db: AppDatabase

)

    : ViewModel() {
    val lengthening = sharedPreferences.getString("lengthening", "0")

    val databaseHelper = DatabaseHelper(db)
    val list:List<Pipe> =db.dbDao().getAll()


    var totalLength: Int = 0
    var degree: Float = 0.0f
    var hypotenuse: Int = 0
    fun calculate(b1: Int, b2: Int, b3: Int, b4: Int, b5: Int) {

        // viewModelScope.launch (Dispatchers.Default){
        try {
            val b8 = hypotenuseCalculate(b1, b2, b3, b4)
            degree = degreeCalculate(b1, b2, b3, b4)
            hypotenuse = b8
            totalLength = totalLengthCalculate(b1, b4, b5, b8)

        } catch (e: Exception) {

        }
        //   }


    }


    private fun hypotenuseCalculate(b1: Int, b2: Int, b3: Int, b4: Int): Int {
        val first = (b3 - b1 - b4).toDouble().pow(2)
        val second = b2.toDouble().pow(2)
        val third = first + second
        val result = third.pow(0.5)
        return result.toInt()

    }

    fun degreeCalculate(b1: Int, b2: Int, b3: Int, b4: Int): Float {
        val first = ((b3 - b1 - b4) / (b2).toFloat())
        val second = atan(first)
        val third = Math.toDegrees(second.toDouble())
        val result = 90 - third

        return result.toFloat()

    }

    fun totalLengthCalculate(b1: Int, b4: Int, b5: Int, b8: Int): Int {
        return (b5 + b4 + b8 + b1) - amountOfLengthening()

    }

    fun amountOfLengthening(): Int {
        return (((lengthening!!.toInt()) * (degree * 2)) / 90).toInt()

    }

    fun insertInDatabase(pipe: Pipe): Boolean {
        var result = false

        try {
            databaseHelper.insert(pipe)
            result = true
        } catch (_: java.lang.Exception) {
            result = false

        }
        return result
    }

    fun checkExist(pipeCode: String): Boolean {
        try {
            return databaseHelper.checkExist(pipeCode)
        } catch (_: Exception) {
            return false
        }
    }

    fun update(
        pipeCode: String,
        totalLength: String,
        angleDegree: String,
        hypotenuseLength: String,
        bottomOfThePipe: String,
        topOfThePillar: String,
        lengthOfPipeTop: String
    ): Int {
        try {
           return databaseHelper.update(
                pipeCode,
                totalLength,
                angleDegree,
                hypotenuseLength,
                bottomOfThePipe,
                topOfThePillar,
                lengthOfPipeTop

            )



        } catch (_: Exception) {
            return -1
        }

    }
}