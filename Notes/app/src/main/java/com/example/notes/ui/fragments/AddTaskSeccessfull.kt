package com.example.notes.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notes.R
import com.example.notes.constance.Constance
import com.example.notes.databinding.FragmentAddTaskSuccessfullBinding
import com.example.notes.ui.taskmanagement.AddTaskFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddTaskSeccessfull : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddTaskSuccessfullBinding
    val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskSuccessfullBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handler.postDelayed({
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, AddTaskFragment())
                .addToBackStack("")
                .commit()
        }, Constance.TIME_SLIDE_INFO_ADD_TASK)
    }
}