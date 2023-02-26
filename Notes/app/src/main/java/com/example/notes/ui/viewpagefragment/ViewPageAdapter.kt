package com.example.notes.ui.viewpagefragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.notes.R
import com.example.notes.ui.viewpagefragment.PageFragment.Companion.getPageFragmentInstance

class ViewPageAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    private val listViewPager = arrayListOf(
        getPageFragmentInstance(R.drawable.illustration_world, "Find project from companies \n everywhere in the world"),
        getPageFragmentInstance(R.drawable.illustration_dollars, "Make money while working \non awesome projects"),
        getPageFragmentInstance(R.drawable.illustration_phone, "Chat with others freelancers \nand develop your network"),
        getPageFragmentInstance(R.drawable.illustration_arrow, "Work hard and level up!"),
        getPageFragmentInstance(R.drawable.illustration_heart, "Enjoy your progress!")
    )

    override fun getCount(): Int = listViewPager.size

    override fun getItem(position: Int): Fragment {
        return listViewPager[position]
    }
}
