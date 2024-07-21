package com.example.calculatehelper.database

import androidx.room.*

@Dao
interface DbDao {

    @Query("SELECT * FROM pipe")
   fun getAll(): List<Pipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun insert( Pipe: Pipe)

    @Query("DELETE FROM Pipe")
    fun delete()

    @Query("SELECT * FROM pipe WHERE pipeCode=:code")
    fun checkExist(code: String): Boolean

    @Query("UPDATE pipe SET totalLength=:totalLength ,angleDegree=:angleDegree, hypotenuseLength=:hypotenuseLength,bottomOfThePipe=:bottomOfThePipe,topOfThePillar=:topOfThePillar,lengthOfPipeTop=:lengthOfPipeTop WHERE pipeCode =:pipeCode")
    fun update(   pipeCode: String,
                  totalLength: String,
                  angleDegree: String,
                  hypotenuseLength: String,
                  bottomOfThePipe: String,
                  topOfThePillar: String,
                  lengthOfPipeTop: String) :Int

}