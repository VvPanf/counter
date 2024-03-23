package ru.vvpanf.counter.viewmodel

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.vvpanf.counter.model.Counter

class CounterViewModel : ViewModel() {
    private val counter: MutableLiveData<Counter> = MutableLiveData(Counter(1, "Qwe", 12, Color.BLUE))
    val counterLive: LiveData<Counter> = counter

    private var oldValue: Int = 0

    fun inc() {
        counter.value!!.value += 1
    }

    fun dec() {
        counter.value!!.value -= 1
    }

    fun changeCount(newValue: Int) {
        oldValue = counter.value!!.value
        counter.value!!.value = newValue
    }

    fun restoreCount() {
        counter.value!!.value = oldValue
    }

    fun changeName(name: String) {
        counter.value!!.name = name
    }
}