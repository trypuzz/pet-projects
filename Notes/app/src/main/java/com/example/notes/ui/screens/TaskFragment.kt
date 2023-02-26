package com.example.notes.ui.screeens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.FragmentTaskBinding
import com.example.notes.repositories.SharePreferencesRepository
import com.example.notes.ui.adapter.TaskRecyclerAdapter
import com.example.notes.ui.screeens.taskmanagement.AddTaskFragment
import com.example.notes.ui.screeens.TaskInfoFragment.Companion.getTaskInfoInstance

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private val viewModel: TaskViewModel by activityViewModels()
    private var rcTaskView: RecyclerView? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharePreferencesRepository = SharePreferencesRepository(requireContext())

        binding.tvAccountName.text = sharePreferencesRepository.getUserName()

        rcTaskView = binding.rcTaskView
        viewModel.listTaskVM.observe(viewLifecycleOwner) {
            (rcTaskView?.adapter as? TaskRecyclerAdapter)?.addNewTask(it)
            viewModel.listsSearchTask.addAll(it)
        }

        viewModel.getTask()

            binding.searchView.doAfterTextChanged {
                viewModel.searchTask(it.toString())
        }

        binding.tvAccountName.text = sharePreferencesRepository.getUserName()

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
                R.id.addTaskMenu -> {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, AddTaskFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }

        rcTaskView?.run {
            adapter = TaskRecyclerAdapter {
                getTaskInfoInstance(it.nameTask, it.messageTask, it.data, it.id)
                    .show(childFragmentManager, "")
            }
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}


