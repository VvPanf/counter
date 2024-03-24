package ru.vvpanf.counter.activity

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment

class ConfirmDeleteDialog(private val onDeleteAction: () -> Unit) : AppCompatDialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle("Delete counter?")
            .setMessage("This action can't be undone")
            .setPositiveButton("Yes") { dialog, which ->
                onDeleteAction()
            }
            .setNegativeButton("Cancel", null)
            .create()
}