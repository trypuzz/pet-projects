package com.example.disneyheroes.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.disneyheroes.R

fun navigationFragments(manager: FragmentManager, fragment: Fragment) {
    manager.beginTransaction()
        .replace(R.id.container, fragment)
        .commit()
}

fun navigationFragmentsAndAddToBackStack(manager: FragmentManager, fragment: Fragment) {
    manager.beginTransaction()
        .replace(R.id.container, fragment)
        .addToBackStack("")
        .commit()
}