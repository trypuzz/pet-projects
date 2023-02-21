package com.example.notes.ui.screeens.registration


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.notes.*
import com.example.notes.databinding.FragmentSignUpBinding
import com.example.notes.repositories.SharePreferencesRepository
import com.example.notes.ui.screeens.TaskFragment
import com.example.notes.ui.screeens.taskmanagement.AddUserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: AddUserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnReturnLogin.setOnClickListener { onClickLogIn() }
        binding.btnSignUp.setOnClickListener { onSignUp() }
    }

    private fun validate(): Boolean {
        val isPasswordValid = validatePassword()
        val isEmailValid = validateEmail()
        val isNameValid = validateName()
        val isLastnameValid = validateLastname()
        return isPasswordValid == null && isEmailValid == null &&
                isNameValid == null && isLastnameValid == null
    }

    private fun validateName(): String? {
        val nameInputLayout = binding.firstNameContainer
        nameInputLayout.editText?.let {
            val result = validateNames(it.text.toString())
            return when (result) {
                is Invalid -> {
                    nameInputLayout.error = this.getString(result.textError)
                    this.getString(result.textError)
                }
                else -> {
                    nameInputLayout.error = null
                    null
                }
            }
        } ?: return null
    }

    private fun validateLastname(): String? {
        val lastnameInputLayout = binding.lastNameSignUpContainer
        lastnameInputLayout.editText?.let {
            val result = validateLastNames(it.text.toString())
            return when (result) {
                is Invalid -> {
                    lastnameInputLayout.error = this.getString(result.textError)
                    this.getString(result.textError)
                }
                else -> {
                    lastnameInputLayout.error = null
                    null
                }
            }
        } ?: return null
    }

    private fun validatePassword(): String? {
        val passwordInputLayout = binding.passwordSignUpContainer
        passwordInputLayout.editText?.let {
            val result = validatePasswords(it.text.toString())
            return when (result) {
                is Invalid -> {
                    passwordInputLayout.error = this.getString(result.textError)
                    this.getString(result.textError)
                }
                else -> {
                    passwordInputLayout.error = null
                    null
                }
            }
        } ?: return null
    }

    private fun validateEmail(): String? {
        val emailInputLayout = binding.emailSignUpContainer
        emailInputLayout.editText?.let {
            val result = validateEmails(it.text.toString())
            return when (result) {
                is Invalid -> {
                    emailInputLayout.error = this.getString(result.textError)
                    this.getString(result.textError)
                }
                else -> {
                    emailInputLayout.error = null
                    null
                }
            }
        } ?: return null
    }

    fun onSignUp() {
        val sharedPreferencesRepository = SharePreferencesRepository(requireContext())
        if (validate()) {
            sharedPreferencesRepository.setUserEmail(binding.emailSignUpEditText.text.toString())
            if ((sharedPreferencesRepository.getUserEmail() == null || (sharedPreferencesRepository.getUserEmail() != null)) ||
                ((sharedPreferencesRepository.getUserEmail() != null) &&
                        (!(sharedPreferencesRepository.getUserEmail()
                            .equals(binding.emailSignUpEditText.text.toString()))))
            ) {
                lifecycleScope.launch(Dispatchers.IO) {
                    sharedPreferencesRepository.getUserEmail()
                        ?.let { viewModel.addUserVM(it) }
                }
                sharedPreferencesRepository.setUserName(binding.firstNameEditText.text.toString())
                sharedPreferencesRepository.setUserEmail(binding.emailSignUpEditText.text.toString())
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, TaskFragment())
                    .addToBackStack("")
                    .commit()
            } else if (sharedPreferencesRepository.getUserEmail()
                    .equals(binding.emailSignUpEditText.text.toString())
            ) {
                Toast.makeText(
                    requireContext(),
                    R.string.app_have_this_user,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun onClickLogIn() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, LogInFragment())
            .addToBackStack("")
            .commit()
    }
}
