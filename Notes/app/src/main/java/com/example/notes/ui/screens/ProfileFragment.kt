package com.example.notes.ui.screeens

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.notes.R
import com.example.notes.dataBase.TaskDataBase
import com.example.notes.databinding.FragmentProfileBinding
import com.example.notes.repositories.SharePreferencesRepository
import com.example.notes.ui.screeens.registration.LogInFragment
import com.example.notes.ui.screeens.taskmanagement.AddTaskFragment
import com.example.notes.ui.screeens.taskmanagement.AddUserViewModel
import com.example.notes.ui.screeens.taskmanagement.ManagementTaskViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private var email: String? = null
    private val viewModel: ManagementTaskViewModel by activityViewModels()
    private val viewModelUser: AddUserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharePreferencesRepository = SharePreferencesRepository(requireContext())
        binding.tvProfileName.text = sharePreferencesRepository.getUserName()

        binding.deleteProfile.setOnClickListener { onClickDeleteAccount() }

        binding.profileLogout.setOnClickListener { onClickLogout() }

        binding.tvDeleteAllTask.setOnClickListener { onClickDeleteAllTaskUser() }

        email = SharePreferencesRepository(requireContext()).getUserEmail()

        lifecycleScope.launch {
            binding.amountTask.text = TaskDataBase.db.taskDao().selectAllTask().size.toString()
        }

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
    }

    fun onClickDeleteAccount() {
        val sharePreferencesRepository = SharePreferencesRepository(requireContext())
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.delete_accaunt)
            .setPositiveButton(R.string.btn_delete) { _, _ ->
                sharePreferencesRepository.deleteUser()
                lifecycleScope.launch(Dispatchers.IO) { viewModelUser.deleteUser(email!!) }
                lifecycleScope.launch(Dispatchers.IO) { viewModel.deleteAllTask() }
                Toast.makeText(requireContext(), R.string.accaunt_deleted, Toast.LENGTH_SHORT)
                    .show()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, LogInFragment())
                    .commit()
            }
            .setNegativeButton(R.string.btn_cancel) { _, _ ->
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, ProfileFragment())
                    .addToBackStack("")
                    .commit()
            }
            .show()
    }

    fun onClickLogout() {
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.logoyt_accaunt)
            .setPositiveButton(R.string.btn_exit) { _, _ ->
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, LogInFragment())
                    .commit()
            }
            .setNegativeButton(R.string.btn_cancel) { _, _ ->
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, ProfileFragment())
                    .addToBackStack("")
                    .commit()
            }
            .show()
    }

    fun onClickDeleteAllTaskUser() {
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.delete_all_task)
            .setPositiveButton(R.string.btn_delete) { _, _ ->
                lifecycleScope.launch(Dispatchers.IO) { viewModel.deleteAllTask() }
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, ProfileFragment())
                    .commit()
            }
            .setNegativeButton(R.string.btn_cancel) { _, _ ->
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, ProfileFragment())
                    .addToBackStack("")
                    .commit()
            }
            .show()
    }
}