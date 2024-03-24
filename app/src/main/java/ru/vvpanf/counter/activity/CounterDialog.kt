package ru.vvpanf.counter.activity

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AppCompatDialogFragment
import ru.vvpanf.counter.R

class CounterDialog(
    private val title: String,
    private val button: String,
    private val onAddAction: (String) -> Unit
) : AppCompatDialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.d_create, null, false)
        val nameCreate = view.findViewById<EditText>(R.id.d_create_name)
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setPositiveButton(button) { dialog, which ->
                val name = nameCreate.text.toString()
                onAddAction(name)
            }
            .setView(view)
            .create()
        return alertDialog
    }
}