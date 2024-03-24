package ru.vvpanf.counter.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.vvpanf.counter.R
import ru.vvpanf.counter.adapter.CountersAdapter
import ru.vvpanf.counter.model.Counter
import ru.vvpanf.counter.utils.EXTRA_ID
import ru.vvpanf.counter.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val mMainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countersAdapter = CountersAdapter(this, createItemListener()).apply {
            setHasStableIds(true)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.counter_list).apply {
            setHasFixedSize(false)
            adapter = countersAdapter
        }

        mMainViewModel.counterListLive.observe(this) { list ->
            countersAdapter.submitList(list)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when(item.itemId) {
            R.id.m_main_create -> {
                createCounter()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun createItemListener() : CountersAdapter.ItemListener =
        object : CountersAdapter.ItemListener {
            override fun onOpen(counter: Counter) =
                startActivity(Intent(this@MainActivity, CounterActivity::class.java).putExtra(EXTRA_ID, counter.id))

            override fun onPlus(counter: Counter) {
                mMainViewModel.inc(counter)
            }

            override fun onMinus(counter: Counter) {
                mMainViewModel.dec(counter)
            }
        }

    private fun createCounter() {
        CounterDialog("Create Counter", "Create") { name ->
            mMainViewModel.create(name, ContextCompat.getColor(this, R.color.orange))
        }.show(supportFragmentManager, null)
    }
}