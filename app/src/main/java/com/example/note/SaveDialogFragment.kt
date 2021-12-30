package com.example.note

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class SaveDialogFragment  : DialogFragment(){

    private var listener: SaveDialogListener? = null

    interface SaveDialogListener {
        fun onDialogPositiveClick()
        fun onDialogNegativeClick()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {

            listener = activity as? SaveDialogListener

            AlertDialog.Builder(it)
                .setTitle(getString(R.string.dialog_title))
                .setNegativeButton(getString(R.string.dialog_negative)) { dialog, which ->
                    listener?.onDialogNegativeClick()
                }
                .setPositiveButton(getString(R.string.dialog_positive)){ dialog, which ->
                    listener?.onDialogPositiveClick()
                }
                .create()
        }?: throw IllegalStateException(getString(R.string.dialog_activity_null_exeption))
    }
}