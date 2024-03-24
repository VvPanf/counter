package ru.vvpanf.counter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.vvpanf.counter.dao.CounterDao
import ru.vvpanf.counter.model.Counter

class MainViewModel(private val counterDao: CounterDao) : ViewModel() {
    val counterListLive: LiveData<List<Counter>>
        get() = counterDao.getAll()

    fun create(name: String, color: Int) = viewModelScope.launch {
        counterDao.save(Counter(name = name, color = color))
    }

    fun inc(counter: Counter) = viewModelScope.launch {
        counterDao.update(with(counter) {
            Counter(id, name, value+1, color)
        })
    }

    fun dec(counter: Counter) = viewModelScope.launch {
        counterDao.update(with(counter) {
            Counter(id, name, value-1, color)
        })
    }
}