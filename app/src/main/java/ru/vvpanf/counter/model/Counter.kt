package ru.vvpanf.counter.model

import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "counter")
data class Counter(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    var name: String = "",
    var value: Int = 0,
    var color: Int = Color.BLUE
)
