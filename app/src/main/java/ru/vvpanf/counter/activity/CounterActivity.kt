package ru.vvpanf.counter.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.vvpanf.counter.R
import ru.vvpanf.counter.utils.EXTRA_ID
import ru.vvpanf.counter.viewmodel.CounterViewModel

class CounterActivity : AppCompatActivity() {
    private lateinit var mTextViewName: TextView
    private lateinit var mTextViewCount: TextView
    private lateinit var mTint: View

    private val mCounterViewModel: CounterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)

        val id = intent.getLongExtra(EXTRA_ID, -1);
        mCounterViewModel.init(id)

        mTextViewName = findViewById(R.id.name)
        mTextViewCount = findViewById(R.id.value)
        mTint = findViewById(R.id.counter_tint)


        mCounterViewModel.counterLive.observe(this) {
            mTextViewName.text = it.name
            mTextViewCount.text = it.value.toString()
            mTint.background.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(it.color, BlendModeCompat.SRC_ATOP)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.counter_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when(item.itemId) {
            R.id.m_counter_edit -> {
                editName()
                true
            }
            R.id.m_counter_delete -> {
                ConfirmDeleteDialog {
                    mCounterViewModel.counterLive.removeObservers(this)
                    mCounterViewModel.delete()
                    finish()
                }
                .show(supportFragmentManager, null)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun editName() {
        CounterDialog("Edit Counter", "Edit") { name ->
            mCounterViewModel.changeName(name)
        }.show(supportFragmentManager, null)
    }
}