package ru.vvpanf.counter.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.vvpanf.counter.model.Counter

@Dao
interface CounterDao {
    @Query("SELECT * FROM counter")
    fun getAll(): LiveData<List<Counter>>

    @Query("SELECT * FROM counter where id = :id")
    fun getById(id: Long): LiveData<Counter>

    @Insert
    suspend fun save(counterEntity: Counter)

    @Update
    suspend fun update(counterEntity: Counter)

    @Delete
    suspend fun delete(counterEntity: Counter)
}