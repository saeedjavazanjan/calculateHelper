package com.example.calculatehelper.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import com.example.calculatehelper.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun provideSharedPref(@ApplicationContext context: Context): SharedPreferences {
        //return PreferenceManager.getDefaultSharedPreferences(context)
        return context.getSharedPreferences("SHARED", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSharedPrefEditor(preferences: SharedPreferences): SharedPreferences.Editor {
        return preferences.edit()
    }

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "pipes"

    ).allowMainThreadQueries()
        .build()

}