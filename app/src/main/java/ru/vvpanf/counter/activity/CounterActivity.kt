package ru.vvpanf.counter.activity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ru.vvpanf.counter.R
import ru.vvpanf.counter.viewmodel.CounterViewModel

class CounterActivity : AppCompatActivity() {
    private lateinit var mTextViewName: TextView
    private lateinit var mTextViewCount: TextView

    private lateinit var mCounterViewModel: CounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)

        mCounterViewModel = ViewModelProvider(this).get(CounterViewModel::class.java)

        mTextViewName = findViewById(R.id.name)
        mTextViewCount = findViewById(R.id.value)

        mCounterViewModel.counterLive.observe(this) {
            mTextViewName.text = it.name
            mTextViewCount.text = it.value.toString()
        }

        findViewById<Button>(R.id.btn_reset).apply {
            setOnClickListener {
                mCounterViewModel.changeCount(0)
                Snackbar.make(it, "Counter was reset", Snackbar.LENGTH_SHORT).apply {
                    setAction("Undo") {
                        mCounterViewModel.restoreCount()
                    }
                    show()
                }
            }
        }
        findViewById<Button>(R.id.btn_minus).apply {
            setOnClickListener {
                mCounterViewModel.dec()
            }
        }
        findViewById<Button>(R.id.btn_plus).apply {
            setOnClickListener {
                mCounterViewModel.inc()
            }
        }
    }
}