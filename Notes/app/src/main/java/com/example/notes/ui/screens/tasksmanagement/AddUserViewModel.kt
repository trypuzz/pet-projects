package com.example.notes.ui.screeens.taskmanagement

import androidx.lifecycle.ViewModel
import com.example.notes.model.User
import com.example.notes.repositories.UserRepository

class AddUserViewModel : ViewModel() {

    private val repository = UserRepository()

    var userAdded: (() -> Unit)? = null

    suspend fun addUserVM(userEmail: String) {
        repository.addUsers(User(userEmail))
        userAdded?.invoke()
    }
    suspend fun deleteUser(email: String) {
        repository.deleteUser(email)
    }
}