package ru.vvpanf.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class CounterActivity : AppCompatActivity() {
    private var mCount = 0
    private var mName = "Counter Name"
    private lateinit var mTextViewName: TextView
    private lateinit var mTextViewCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)
        mCount = savedInstanceState?.getInt("count") ?: 0
        mTextViewName = findViewById<TextView?>(R.id.name).apply {
            text = mName
        }
        mTextViewCount = findViewById<TextView?>(R.id.value).apply {
            text = mCount.toString()
        }
        findViewById<Button>(R.id.btn_reset).apply {
            setOnClickListener {
                val oldValue = mCount
                changeCount(0)
                Snackbar.make(it, "Counter was reset", Snackbar.LENGTH_SHORT).apply {
                    setAction("Undo") {
                        changeCount(oldValue)
                        drawCount()
                    }
                    show()
                }
                drawCount()
            }
        }
        findViewById<Button>(R.id.btn_minus).apply {
            setOnClickListener {
                dec()
                drawCount()
            }
        }
        findViewById<Button>(R.id.btn_plus).apply {
            setOnClickListener {
                inc()
                drawCount()
            }
        }
        drawCount()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("count", mCount)
        super.onSaveInstanceState(outState)
    }

    private fun inc() {
        changeCount(mCount + 1)
    }

    private fun dec() {
        changeCount(mCount - 1)
    }

    private fun changeCount(value: Int) {
        mCount = value
    }

    private fun drawCount() {
        mTextViewCount.text = mCount.toString()
    }
}