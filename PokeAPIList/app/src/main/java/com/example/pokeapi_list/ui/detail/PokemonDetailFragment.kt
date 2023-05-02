package com.example.pokeapi_list.ui.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pokeapi_list.R
import com.example.pokeapi_list.databinding.FragmentPokemonDetailBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PokemonDetailFragment : Fragment() {

    private val viewModel: DetailViewModel by activityViewModels()
    private val args: PokemonDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPokemonDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val toolbar = binding.toolbar
        toolbar.setNavigationIcon(R.drawable.ic_back_button)

        toolbar.setNavigationOnClickListener {
            binding.root.findNavController().navigateUp()
        }

        var currentPokemon = viewModel.onPokemonOpened(args.pokemonName)

        binding.apply {
            Glide.with(root)
                .load(currentPokemon?.sprites?.front_default)
                .into(binding.image)
        }

        binding.name.text = currentPokemon?.name?.capitalize()
        binding.height.text = "Height: ${currentPokemon?.height?.div(0.1)?.toInt().toString()} cm"
        binding.weight.text = "Weight: ${currentPokemon?.weight.toString()} kg"
        binding.types.text = currentPokemon?.types?.joinToString(
            prefix = "Type: ",
            separator = ", ",
            transform = { it.type?.name.toString() }
        )

        return binding.root
    }
}