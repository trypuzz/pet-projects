package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.notes.constance.Constance
import com.example.notes.databinding.ActivityLogInBinding

class LoginActivity : AppCompatActivity() {


    lateinit var bindingClass: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        emailLoginListener()
        passwordLoginListener()
        bindingClass.buttonLogin.setOnClickListener { submitForm() }
    }

    private fun submitForm() {
        bindingClass.emailContainer.helperText = validEmail()
        bindingClass.passwordContainer.helperText = validPassword()

        val validEmail = bindingClass.emailContainer.helperText == null
        val validPassword = bindingClass.passwordContainer.helperText == null

        if(validEmail && validPassword)
            startActivity(Intent(this, SignUpActivity::class.java))
        else
            invalidForm()
    }

    private fun invalidForm() {
        var message = ""
        if(bindingClass.emailContainer.helperText != null)
            message += "Error Email"
        if(bindingClass.passwordContainer.helperText != null)
            message += "\nError Password"

        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun emailLoginListener() {
        bindingClass.emailEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                bindingClass.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = bindingClass.emailEditText.text.toString()
        if (TextUtils.isEmpty(emailText)) {
            return "Обязательное поле"
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Неверная форма Email"
        }
        return null
    }

    private fun passwordLoginListener() {
        bindingClass.passwordEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                bindingClass.passwordContainer.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String? {
        val passwordText = bindingClass.passwordEditText.text.toString()
        if (TextUtils.isEmpty(passwordText)) {
            return "Обязательное поле"
        }
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

    fun onLogIn(view: View) {
        submitForm()
    }

    fun onClickSignUp(view: View){
        startActivity(Intent(this, SignUpActivity::class.java))
    }
}