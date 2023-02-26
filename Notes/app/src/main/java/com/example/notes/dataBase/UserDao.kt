package com.example.notes.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.notes.model.User

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user: User)

    @Query("DELETE FROM users WHERE email = (:email)")
    suspend fun deleteUser(email: String)

    @Query("SELECT * FROM users")
    suspend fun getAllUser(): List<User>
}