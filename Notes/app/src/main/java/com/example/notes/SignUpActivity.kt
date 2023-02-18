package com.example.notes

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notes.constance.Constance
import com.example.notes.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    lateinit var bindingClass : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        firstNameSignUpListener()
        lastNameSignUpListener()
        emailSignUpListener()
        passwordSignUpListener()
        bindingClass.buttonSignUp.setOnClickListener { submitForm() }
    }

    private fun submitForm() {
        bindingClass.firstNameContainer.helperText = validName()
        bindingClass.lastNameSignUpContainer.helperText = validLastName()
        bindingClass.emailSignUpContainer.helperText = validEmailSignUp()
        bindingClass.passwordSignUpContainer.helperText = validPasswordSignUp()

        val validName = bindingClass.firstNameContainer.helperText == null
        val validLastname = bindingClass.lastNameSignUpContainer.helperText == null
        val validEmail = bindingClass.emailSignUpContainer.helperText == null
        val validPassword = bindingClass.passwordSignUpContainer.helperText == null

        if(validName && validLastname && validEmail && validPassword)
            startActivity(Intent(this, LogInActivity::class.java))
        else
            invalidForm()
    }

    private fun invalidForm() {
        var message = ""
        if(bindingClass.firstNameContainer.helperText != null)
            message += "Error Name"
        if(bindingClass.lastNameSignUpContainer.helperText != null)
            message += "\nError Lastname"
        if(bindingClass.emailSignUpContainer.helperText != null)
            message += "\nError Email"
        if(bindingClass.passwordSignUpContainer.helperText != null)
            message += "\nError Password"

        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun firstNameSignUpListener() {
        bindingClass.firstNameEditText.setOnFocusChangeListener { _, focused ->
            if(!focused){
                bindingClass.firstNameContainer.helperText = validName()
            }
        }
    }

    private fun validName(): String? {
        val nameText = bindingClass.firstNameEditText.text.toString()
        if(nameText.length > Constance.MAX_NAME_TEXT){
            return "Максимальное количество символов ${Constance.MAX_NAME_TEXT}"
        }
        if(nameText.length < Constance.MIN_NAME_TEXT){
            return "Минимальное количество символов ${Constance.MIN_NAME_TEXT}"
        }
        return null
    }

    private fun lastNameSignUpListener() {
        bindingClass.lastNameEditText.setOnFocusChangeListener { _, focused ->
            if(!focused){
                bindingClass.lastNameSignUpContainer.helperText = validLastName()
            }
        }
    }

    private fun validLastName(): String? {
        val lastNameText = bindingClass.lastNameEditText.text.toString()
        if(lastNameText.length > Constance.MAX_LASTNAME_TEXT){
            return "Максимальное количество символов ${Constance.MAX_LASTNAME_TEXT}"
        }
        if(lastNameText.length < Constance.MIN_LASTNAME_TEXT){
            return "Минимальное количество символов ${Constance.MIN_LASTNAME_TEXT}"
        }
        return null
    }

    private fun emailSignUpListener() {
        bindingClass.emailSignUpEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                bindingClass.emailSignUpContainer.helperText = validEmailSignUp()
            }
        }
    }

    private fun validEmailSignUp(): String? {
        val emailText = bindingClass.emailSignUpEditText.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Неверная форма Email"
        }
        return null
    }

    private fun passwordSignUpListener() {
        bindingClass.passwordSignUpEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                bindingClass.passwordSignUpContainer.helperText = validPasswordSignUp()
            }
        }
    }

    private fun validPasswordSignUp(): String? {
        val passwordText = bindingClass.passwordSignUpEditText.text.toString()
        if (passwordText.length < Constance.MIN_PASS_TEXT){
            return "Минимальное количество символов ${Constance.MIN_PASS_TEXT}"
        }
        if (passwordText.length > Constance.MAX_PASS_TEXT){
            return "Максимальное количество символов ${Constance.MAX_PASS_TEXT}"
        }
        if (!passwordText.matches(".*[a-z].*".toRegex())){
            return "Пароль должен содержать как минимум одну маленькую букву"
        }
        if (!passwordText.matches(".*[A-Z].*".toRegex())){
            return "Пароль должен содержать как минимум одну большую букву"
        }
        if (!passwordText.matches(".*[@#\$%^&+=].*".toRegex())){
            return "Пароль должен содержать как минимум один спец.символ (@#\$%^&+=)"
        }
        if (!passwordText.matches(".*[0-9].*".toRegex())){
            return "Пароль должен содержать как минимум одну цифру"
        }
        return null
    }

    fun onSignUp(view: View){
        submitForm()
    }

    fun onClickLogIn(view: View){
        startActivity(Intent(this, LogInActivity::class.java))
    }

}
