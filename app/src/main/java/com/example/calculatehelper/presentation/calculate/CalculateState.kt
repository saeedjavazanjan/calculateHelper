package com.example.calculatehelper.presentation.calculate

sealed class CalculateState<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : CalculateState<T>(data)
    class Error<T>(message: String, data: T? = null) : CalculateState<T>(data, message)
    class Loading<T>(data: T? = null) : CalculateState<T>(data)

}