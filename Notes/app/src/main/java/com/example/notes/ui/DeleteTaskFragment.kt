package com.example.notes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.notes.R

class DeleteTaskFragment: Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_delete_task,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ImageView>(R.id.btnCancelDelete).setOnClickListener {
        }

        view.findViewById<ImageView>(R.id.btnDeleteTask).setOnClickListener{
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = DeleteTaskFragment()
    }


}