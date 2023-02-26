package com.example.disneyheroes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.disneyheroes.R
import com.example.disneyheroes.databinding.FragmentStartAppBinding
import com.example.disneyheroes.ui.listdisneyheroes.ListHeroesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartAppFragment : Fragment() {

    private lateinit var binding: FragmentStartAppBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonGetStarted.setOnClickListener {
            parentFragmentManager.commit {
                setCustomAnimations(
                    R.anim.anim_open_list_heroes_fragment,
                    R.anim.anim_close_list_heroes_fragment,
                    R.anim.anim_open_list_heroes_fragment,
                    R.anim.anim_close_list_heroes_fragment
                )
                replace(R.id.container, ListHeroesFragment())
                addToBackStack("StartAppFragment")
            }
        }
    }
}