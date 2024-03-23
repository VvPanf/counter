package ru.vvpanf.counter.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import ru.vvpanf.counter.model.Counter
import ru.vvpanf.counter.adapter.CountersAdapter
import ru.vvpanf.counter.R
import ru.vvpanf.counter.utils.getCounterList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val counters: List<Counter> = getCounterList()
        val countersAdapter = CountersAdapter(this, counters)
        countersAdapter.setHasStableIds(true)
        findViewById<RecyclerView>(R.id.counter_list).also {
            it.setHasFixedSize(false)
            it.adapter = countersAdapter
        }
    }
}