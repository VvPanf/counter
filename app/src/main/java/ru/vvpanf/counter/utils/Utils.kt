package ru.vvpanf.counter.utils

import android.graphics.Color
import ru.vvpanf.counter.model.Counter

fun getCounterList(): List<Counter> = listOf(
    Counter(1, "Counter 1", 0, Color.BLUE),
    Counter(2, "Counter 2", 5, Color.MAGENTA),
    Counter(3, "Counter 3", 0, Color.RED)
)