package com.example.pokeapi_list.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.example.pokeapi_list.databinding.FragmentPokemonListBinding
import com.example.pokeapi_list.ui.PokemonListAdapter

@AndroidEntryPoint
class PokemonListFragment : Fragment() {

    private val viewModel: ListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPokemonListBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = PokemonListAdapter(PokemonListener { pokemon ->
            val direction = PokemonListFragmentDirections
                .actionPokemonListFragmentToPokemonDetailFragment(pokemon.name)
            findNavController().navigate(direction)
        })

        val adapter = binding.recyclerView.adapter as PokemonListAdapter
        viewModel.pokemons.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
        return binding.root
    }
}