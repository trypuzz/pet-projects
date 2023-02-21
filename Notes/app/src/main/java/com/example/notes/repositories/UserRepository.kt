package com.example.notes.repositories

import com.example.notes.dataBase.TaskDataBase
import com.example.notes.model.User

class UserRepository {

    suspend fun addUsers(user: User) {
        TaskDataBase.db.userDao().addUser(user)
    }

    suspend fun deleteUser(email: String) {
        TaskDataBase.db.userDao().deleteUser(email)
    }

    suspend fun getListUser(): ArrayList<User> {
        return TaskDataBase.db.userDao().getAllUser() as ArrayList<User>
    }
}
