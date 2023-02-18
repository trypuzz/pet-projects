package com.example.notes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notes.R
import com.example.notes.dataBase.TaskDataBase
import com.example.notes.databinding.FragmentSearchTaskBinding
import com.example.notes.ui.taskmanagement.AddTaskFragment


class SearchTaskFragment : Fragment() {

    private lateinit var binding: FragmentSearchTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchTaskBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.bottomNavigationMenu.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.profileMenu -> {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, ProfileFragment())
                        .commit()
                    true
                }
                R.id.taskMenu -> {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, TaskFragment())
                        .commit()
                    true
                }
                R.id.searchMenu -> {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, SearchTaskFragment())
                        .commit()
                    true
                }
                R.id.addTaskMenu -> {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, AddTaskFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }

    val  searchList = TaskDataBase.db.taskDao().selectAllTask()
}