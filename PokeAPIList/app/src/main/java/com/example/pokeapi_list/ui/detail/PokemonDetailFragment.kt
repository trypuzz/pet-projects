package com.example.pokeapi_list.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pokeapi_list.R
import com.example.pokeapi_list.databinding.FragmentPokemonDetailBinding

class PokemonDetailFragment : Fragment() {

    private val viewModel: PokemonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) : View? {
        val binding = FragmentPokemonDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val toolbar = binding.toolbar
        toolbar.setNavigationIcon(R.drawable.ic_back_button)

        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        return binding.root
    }
}