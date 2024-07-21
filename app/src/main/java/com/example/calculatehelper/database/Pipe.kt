package com.example.calculatehelper.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pipe(

    @PrimaryKey(autoGenerate = true) val pipeID: Int,
    @ColumnInfo(name = "pipeCode") val code: String?,
    @ColumnInfo(name = "totalLength") val totalLength: String?,
    @ColumnInfo(name = "angleDegree") val angleDegree: String?,
    @ColumnInfo(name = "hypotenuseLength") val hypotenuseLength: String?,
    @ColumnInfo(name = "bottomOfThePipe") val bottomOfThePipe: String?,
    @ColumnInfo(name = "topOfThePillar") val topOfThePillar: String?,
    @ColumnInfo(name = "lengthOfPipeTop") val lengthOfPipeTop: String?


) {
    constructor(
        code: String, totalLength: String, angleDegree: String,
        hypotenuseLength: String, bottomOfThePipe: String,
        topOfThePillar: String, lengthOfPipeTop: String
    )
            : this(
        0,
        code,
        totalLength,
        angleDegree,
        hypotenuseLength,
        bottomOfThePipe,
        topOfThePillar,
        lengthOfPipeTop
    )

}