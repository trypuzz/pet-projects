package com.example.pokeapi_list.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.pokeapi_list.ui.PokemonListAdapter
import com.example.pokeapi_list.ui.PokemonListener
import com.example.pokeapi_list.R
import com.example.pokeapi_list.databinding.FragmentPokemonListBinding
import com.example.pokeapi_list.ui.list.PokemonListAdapter
import com.example.pokeapi_list.ui.PokemonListener
import kotlinx.coroutines.launch

class PokemonListFragment : Fragment() {

    private val viewModel: ListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPokemonListBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = PokemonListAdapter(PokemonListener { pokemon ->
            val direction = PokemonListFragmentDirections
                .actionPokemonListFragmentToPokemonDetailFragment(pokemon.name)
            findNavController().navigate(direction)
        })

        lifecycleScope.launch {
            val adapter = binding.recyclerView.adapter as PokemonListAdapter
            viewModel.pokemons.collect {
                adapter.submitData(it)
            }


        }
        return binding.root
    }

}