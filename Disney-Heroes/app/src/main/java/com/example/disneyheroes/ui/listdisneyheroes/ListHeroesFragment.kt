package com.example.disneyheroes.ui.listdisneyheroes

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.disneyheroes.R
import com.example.disneyheroes.databinding.FragmentListHeroesBinding
import com.example.disneyheroes.models.DisneyHero
import com.example.disneyheroes.ui.disneyhero.HeroFragment
import com.example.disneyheroes.ui.listdisneyheroes.adapter.FavoriteHeroesAdapter
import com.example.disneyheroes.ui.listdisneyheroes.adapter.HeroesAdapter
import com.example.disneyheroes.utils.navigationFragments
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val DELAY = 1000L
private const val DURATION = 1000L

@AndroidEntryPoint
class ListHeroesFragment : Fragment() {

    private lateinit var binding: FragmentListHeroesBinding

    private val viewModel: ListHeroesViewModel by viewModels()

    private lateinit var recyclerFavoriteHeroes: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListHeroesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val valueAnimator = ValueAnimator.ofFloat(0F, 1F)
        valueAnimator.addUpdateListener {
            binding.imageDisneyHeroes.scaleX = it.animatedValue as Float
            binding.imageDisneyHeroes.scaleY = it.animatedValue as Float
        }
        valueAnimator.duration = DURATION
        valueAnimator.start()

        lifecycleScope.launch {
            viewModel.flowListHeroes.collectLatest { pagingData ->
                initListHeroes(pagingData)
            }
        }
        viewModel.getListHeroes()

        binding.apply {
            buttonMyHeroes.setOnClickListener {
                ObjectAnimator.ofFloat(buttonMyHeroes, View.ALPHA, 0.2F, 1F)
                    .setDuration(DURATION)
                    .start()

                viewModel.listFavoriteHeroes.observe(viewLifecycleOwner) {
                    initListFavoriteHeroes(it)
                }
                viewModel.getListFavoriteHeroes()

                buttonMyHeroes.setImageResource(R.drawable.ic_baseline_favorite_30)
                buttonMyHeroes.setBackgroundResource(R.drawable.bg_image_button_checked)
                textMy.setTextColor(root.context.getColor(R.color.color_image_button_checked))
                textAll.setTextColor(root.context.getColor(R.color.color_image_button_unchecked))
                buttonAllHeroes.setBackgroundResource(R.drawable.bg_image_button_unchecked)
            }

            buttonAllHeroes.setOnClickListener {
                ObjectAnimator.ofFloat(buttonAllHeroes, View.ALPHA, 0.2F, 1F)
                    .setDuration(DURATION)
                    .start()

                lifecycleScope.launch {
                    delay(DELAY)
                    navigationFragments(parentFragmentManager, ListHeroesFragment())
                }

                buttonAllHeroes.setBackgroundResource(R.drawable.bg_image_button_checked)
                textAll.setTextColor(root.context.getColor(R.color.color_image_button_checked))
                buttonMyHeroes.setImageResource(R.drawable.ic_baseline_favorite_border_30)
                buttonMyHeroes.setBackgroundResource(R.drawable.bg_image_button_unchecked)
                textMy.setTextColor(root.context.getColor(R.color.color_image_button_unchecked))
            }
        }

        viewModel.onError = {
            binding.apply {
                bannerView.post {
                    bannerView.visibility = View.VISIBLE
                    val animOpen = AnimationUtils.loadAnimation(
                        requireContext(),
                        R.anim.anim_open_banner_network_error
                    )
                    bannerView.startAnimation(animOpen)
                    listHeroes.visibility = View.INVISIBLE
                    buttonAllHeroes.isClickable = false
                    buttonMyHeroes.isClickable = false

                    bannerView.setClickCancel {
                        val animClose = AnimationUtils.loadAnimation(
                            requireContext(),
                            R.anim.anim_close_banner_network_error
                        )
                        bannerView.startAnimation(animClose)
                        bannerView.visibility = View.GONE
                        listHeroes.visibility = View.VISIBLE
                        buttonAllHeroes.isClickable = true
                        buttonMyHeroes.isClickable = true
                    }

                    bannerView.setClickRepeat {
                        bannerView.visibility = View.GONE
                        listHeroes.visibility = View.VISIBLE
                        buttonAllHeroes.isClickable = true
                        buttonMyHeroes.isClickable = true
                        navigationFragments(parentFragmentManager, ListHeroesFragment())
                    }
                }
            }
        }
    }

    private suspend fun initListHeroes(listHeroes: PagingData<DisneyHero>) {
        binding.listHeroes.run {
            if (adapter == null) {
                adapter = HeroesAdapter {
                    parentFragmentManager.commit {
                        setCustomAnimations(
                            R.anim.anim_open_hero_fragment,
                            R.anim.anim_close_list_heroes_fragment,
                            R.anim.anim_open_hero_fragment,
                            R.anim.anim_close_list_heroes_fragment
                        )
                        replace(R.id.container, HeroFragment.newInstance(it.id.toString()))
                        addToBackStack("ListHeroesFragment")
                    }
                }
                layoutManager = GridLayoutManager(requireContext(), 2)
            }
            (adapter as? HeroesAdapter)?.submitData(listHeroes)
        }
    }

    private fun initListFavoriteHeroes(listFavoriteHeroes: List<DisneyHero>) {
        recyclerFavoriteHeroes = binding.listHeroes
        recyclerFavoriteHeroes.run {
            adapter = FavoriteHeroesAdapter {
                parentFragmentManager.commit {
                    setCustomAnimations(
                        R.anim.anim_open_hero_fragment,
                        R.anim.anim_close_list_heroes_fragment,
                        R.anim.anim_open_hero_fragment,
                        R.anim.anim_close_list_heroes_fragment
                    )
                    replace(R.id.container, HeroFragment.newInstance(it.id.toString()))
                    addToBackStack("ListHeroesFragment")
                }
            }
            layoutManager = GridLayoutManager(requireContext(), 2)
            (recyclerFavoriteHeroes.adapter as? FavoriteHeroesAdapter)?.setListFavoriteHero(
                listFavoriteHeroes
            )
        }
    }
}