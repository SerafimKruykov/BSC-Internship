package com.example.note

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class SaveDialogFragment  : DialogFragment(){

    private lateinit var listener: SaveDialogListener

    interface SaveDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            listener = activity as SaveDialogListener

            val builder = AlertDialog.Builder(it)
            builder
                .setTitle(getString(R.string.dialog_title))
                .setNegativeButton(getString(R.string.dialog_negative)) { dialog, which ->
                    listener.onDialogNegativeClick(this)
                }
                .setPositiveButton(getString(R.string.dialog_positive)){ dialog, which ->
                    listener.onDialogPositiveClick(this)
                }
            builder.create()
        }?: throw IllegalStateException(getString(R.string.dialog_activity_null_exeption))
    }
}