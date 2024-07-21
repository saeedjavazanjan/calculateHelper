package com.example.calculatehelper.database


class DatabaseHelper(private val appDatabase: AppDatabase) {
    val userDao = appDatabase.dbDao()
    fun insert(pipe: Pipe) = userDao.insert(pipe)

    fun checkExist(pipeCode: String) = userDao.checkExist(pipeCode)

    fun update(
        pipeCode: String,
        totalLength: String,
        angleDegree: String,
        hypotenuseLength: String,
        bottomOfThePipe: String,
        topOfThePillar: String,
        lengthOfPipeTop: String
    ) = userDao.update(
        pipeCode,
        totalLength,
        angleDegree,
        hypotenuseLength,
        bottomOfThePipe,
        topOfThePillar,
        lengthOfPipeTop
    )

    fun delete() = userDao.delete()
}