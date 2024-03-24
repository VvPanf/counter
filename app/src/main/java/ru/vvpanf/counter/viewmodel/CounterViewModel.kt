package ru.vvpanf.counter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.vvpanf.counter.dao.CounterDao
import ru.vvpanf.counter.model.Counter

class CounterViewModel(private val counterDao: CounterDao) : ViewModel() {
    lateinit var counterLive: LiveData<Counter>

    private var oldValue: Int = 0

    fun init(id: Long) {
        counterLive = counterDao.getById(id)
        }

    fun inc() = viewModelScope.launch {
        counterLive.value?.apply {
            value += 1
            counterDao.update(this)
        }
    }

    fun dec() = viewModelScope.launch {
        counterLive.value?.apply {
            value -= 1
            counterDao.update(this)
        }
    }

    fun changeCount(newValue: Int) = viewModelScope.launch {
        counterLive.value?.apply {
            oldValue = value
            value = newValue
            counterDao.update(this)
        }
    }

    fun restoreCount() = viewModelScope.launch {
        counterLive.value?.apply {
            value = oldValue
            oldValue = 0
            counterDao.update(this)
        }
    }

    fun changeName(newName: String) = viewModelScope.launch {
        counterLive.value?.apply {
            name = newName
            counterDao.update(this)
        }
    }

    fun delete() = viewModelScope.launch {
        counterLive.value?.apply {
            counterDao.delete(this)
        }
    }
}