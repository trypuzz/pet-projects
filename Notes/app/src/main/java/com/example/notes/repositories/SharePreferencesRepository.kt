package com.example.notes.repositories

import android.content.Context
import androidx.core.content.edit


private const val USER_PREFERENCES = "user_preferences"
private const val GLOBAL_PREFERENCES = "global_preferences"
private const val USER_NAME = "user_name"
private const val USER_EMAIL = "user_email"

class SharePreferencesRepository(context: Context) {

    private val userPreferences =
        context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)

    private val globalPreferences =
        context.getSharedPreferences(GLOBAL_PREFERENCES, Context.MODE_PRIVATE)

    fun setUserName(userName: String) {
        userPreferences.edit {
            putString(USER_NAME, userName)
        }
    }

    fun getUserName(): String? {
        return userPreferences.getString(USER_NAME, null)
    }


    fun setUserEmail(userName: String) {
        globalPreferences.edit {
            putString(USER_EMAIL, userName)
        }
    }

    fun getUserEmail(): String? {
        return globalPreferences.getString(USER_EMAIL, null)
    }

    fun deleteUser(){
        globalPreferences.edit{
            clear()
        }
    }
}