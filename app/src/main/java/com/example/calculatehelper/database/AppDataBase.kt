package com.example.calculatehelper.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Pipe::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dbDao(): DbDao
}