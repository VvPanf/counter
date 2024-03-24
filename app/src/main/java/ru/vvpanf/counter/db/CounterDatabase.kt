package ru.vvpanf.counter.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.vvpanf.counter.dao.CounterDao
import ru.vvpanf.counter.model.Counter

@Database(entities = [Counter::class], version = 1)
abstract class CounterDatabase : RoomDatabase() {
    abstract fun counterDao(): CounterDao
}