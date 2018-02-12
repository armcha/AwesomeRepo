package io.github.armcha.awesomeproject.presentation.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import io.github.armcha.awesomeproject.R
import kotlinx.android.synthetic.main.input_dialog.view.*

class InputDialog : DialogFragment() {

    private var inputDialogListener: InputDialogListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        inputDialogListener = context as? InputDialogListener
    }

    override fun onDetach() {
        inputDialogListener = null
        super.onDetach()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val rootView = LayoutInflater.from(activity).inflate(R.layout.input_dialog, null)

        val builder = AlertDialog.Builder(activity).apply {
            setTitle(getString(R.string.name_surname))
            setView(rootView)
            setPositiveButton(getString(R.string.add), null)
            setNegativeButton(getString(android.R.string.cancel)) { dialog, _ -> dialog.dismiss() }
        }

        val alert = builder.create()
        alert.setOnShowListener({
            with(rootView) {
                val textChangeListener = object : TextWatcher by TextChangeListener() {
                    override fun afterTextChanged(s: Editable?) {
                        nameEditText.error = null
                        surNameEditText.error = null
                    }
                }
                nameEditText.addTextChangedListener(textChangeListener)
                surNameEditText.addTextChangedListener(textChangeListener)

                val positiveButton = alert.getButton(AlertDialog.BUTTON_POSITIVE)
                positiveButton.setOnClickListener {
                    val name = nameEditText.text.toString()
                    val surname = surNameEditText.text.toString()
                    if (!isValidString(name)) {
                        nameEditText.error = getString(R.string.empty_name_error)
                    }
                    if (!isValidString(surname)) {
                        surNameEditText.error = getString(R.string.empty_surname_error)
                    }
                    if (isValidString(name) && isValidString(surname)) {
                        inputDialogListener?.onDialogPositiveAction(name, surname)
                        dialog.dismiss()
                    }
                }
            }
        })

        return alert
    }

    private fun isValidString(input: String) = input.isNotEmpty() || input.isNotBlank()

    interface InputDialogListener {

        fun onDialogPositiveAction(name: String, surname: String)
    }
}