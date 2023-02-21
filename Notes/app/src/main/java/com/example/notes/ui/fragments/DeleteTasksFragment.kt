package com.example.notes.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notes.databinding.FragmentDeleteTaskBinding

class DeleteTasksFragment : Fragment() {

    private lateinit var binding: FragmentDeleteTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("MyLog", "MyLog1111")
        binding = FragmentDeleteTaskBinding.inflate(inflater)
        return binding.root

    }
}