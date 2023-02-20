package com.example.triviaquiz.util.common

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.triviaquiz.R

fun <T> Spinner.setupSpinner(
    context: Context,
    data: Array<T>,
    nothingSelected: () -> Unit,
    itemSelected: (position: Int) -> Unit
) {
    this.adapter = ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, data)
    this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {
            nothingSelected()
        }

        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            itemSelected(position)
        }
    }
}