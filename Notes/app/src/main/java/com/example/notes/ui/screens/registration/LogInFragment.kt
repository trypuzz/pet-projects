package com.example.notes.ui.screeens.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.notes.*
import com.example.notes.databinding.FragmentLogInBinding
import com.example.notes.repositories.SharePreferencesRepository
import com.example.notes.ui.screeens.TaskFragment

class LogInFragment : Fragment() {

    private lateinit var binding: FragmentLogInBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener { onLogIn() }
        binding.btnSignUpReturn.setOnClickListener { onClickReturnSignUp() }
    }

    private fun validate(): Boolean {
        val isPasswordValid = validatePassword()
        val isEmailValid = validateEmail()
        return isPasswordValid == null && isEmailValid == null
    }

    private fun validatePassword(): String? {
        val passwordInputLayout = binding.passwordContainer
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
        val emailInputLayout = binding.emailContainer
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

    fun onLogIn() {
        val sharedPreferencesRepository = SharePreferencesRepository(requireContext())
        if (validate()) {
            if ((sharedPreferencesRepository.getUserEmail() != null) && (sharedPreferencesRepository.getUserEmail()
                    .equals(binding.emailEditText.text.toString()))
            ) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, TaskFragment())
                    .addToBackStack("")
                    .commit()
            } else if (!(sharedPreferencesRepository.getUserEmail()
                    .equals(binding.emailEditText.text.toString())) || (sharedPreferencesRepository.getUserEmail() == null)
            ) {
                Toast.makeText(requireContext(), R.string.user_no_registration, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun onClickReturnSignUp() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, SignUpFragment())
            .addToBackStack("")
            .commit()
    }
}

