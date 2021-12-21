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
            listener = getTargetFragment() as SaveDialogListener
            val builder = AlertDialog.Builder(it)
            builder
                .setTitle("Сохранить заметку?")
                .setNegativeButton("Отмена") { dialog, which ->
                    listener.onDialogNegativeClick(this)
                }
                .setPositiveButton("Сохранить"){ dialog, which ->
                    listener.onDialogPositiveClick(this)
                }
            builder.create()
        }?: throw IllegalStateException("Activity cannot be null")
    }
}