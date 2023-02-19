package com.example.disneyheroes.ui.disneyhero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.disneyheroes.R
import com.example.disneyheroes.databinding.FragmentHeroBinding
import com.example.disneyheroes.models.DisneyHero
import com.example.disneyheroes.models.InfoHero
import com.example.disneyheroes.ui.disneyhero.infoheroadapter.InfoHeroAdapter
import com.example.disneyheroes.ui.listdisneyheroes.ListHeroesFragment
import com.example.disneyheroes.utils.loadUrl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HeroFragment : Fragment() {

    private lateinit var binding: FragmentHeroBinding

    private val viewModel: HeroViewModel by viewModels()

    private var isFavoriteHero = false

    private lateinit var currentHero: DisneyHero

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            buttonBack.setOnClickListener {
                parentFragmentManager.commit {
                    setCustomAnimations(
                        R.anim.anim_open_hero_fragment,
                        R.anim.anim_close_list_heroes_fragment,
                        R.anim.anim_open_hero_fragment,
                        R.anim.anim_close_list_heroes_fragment
                    )
                    replace(R.id.container, ListHeroesFragment())
                }
            }

            buttonLike.setOnClickListener {
                if (!currentHero.isFavorite) {
                    showBannerView(
                        title = getString(R.string.hero_successfully_added_to_favorites),
                        imageId = R.drawable.ic_baseline_done_28
                    )
                    buttonLike.setImageResource(R.drawable.ic_baseline_favorite_30)
                    viewModel.setFavoriteHero(currentHero.id.toString(), true)
                    viewModel.selectFavoriteHero(currentHero)
                    val anim =
                        AnimationUtils.loadAnimation(requireContext(), R.anim.anim_button_like)
                    buttonLike.startAnimation(anim)
                } else {
                    showBannerView(
                        title = getString(R.string.hero_successfully_removed_from_favorites),
                        imageId = R.drawable.ic_baseline_remove_circle_outline_28
                    )
                    buttonLike.setImageResource(R.drawable.ic_baseline_favorite_border_30)
                    viewModel.setFavoriteHero(currentHero.id.toString(), false)
                    viewModel.selectFavoriteHero(currentHero)
                }
            }
        }

        viewModel.oneHero.observe(viewLifecycleOwner) {
            currentHero = it
            binding.textNameHero.text = it.name
            binding.imageHero.loadUrl(it.imageUrl)
            setList(it.listInfo)
            val flagFavorite = viewModel.getFavoriteHero(currentHero.id.toString())
            isFavoriteHero = if (isFavoriteHero != flagFavorite) {
                binding.buttonLike.setImageResource(R.drawable.ic_baseline_favorite_30)
                true
            } else {
                binding.buttonLike.setImageResource(R.drawable.ic_baseline_favorite_border_30)
                false
            }
        }

        arguments?.getString(ID_HERO)?.let {
            viewModel.getInfoOneHero(it)
        }
    }

    private fun setList(list: ArrayList<InfoHero>) {
        binding.listCategoriesHero.run {
            if (adapter == null) {
                adapter = InfoHeroAdapter()
                layoutManager = LinearLayoutManager(requireContext())
            }
            (adapter as? InfoHeroAdapter)?.submitList(list)
        }
    }

    private fun showBannerView(title: String, imageId: Int) {
        binding.apply {
            bannerView.visibility = View.VISIBLE
            bannerView.setTitle(title)
            bannerView.setImageAddDeleteHero(imageId)
            buttonBack.isClickable = false
            buttonLike.isClickable = false
            val animOpen = AnimationUtils.loadAnimation(
                requireContext(),
                R.anim.anim_open_banner_add_delete_favorite
            )
            bannerView.startAnimation(animOpen)

            bannerView.setClickClose {
                buttonBack.isClickable = true
                buttonLike.isClickable = true
                val animClose = AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.anim_close_banner_add_delete_favorite
                )
                bannerView.startAnimation(animClose)
            }

            lifecycleScope.launch {
                delay(5_000)
                bannerView.visibility = View.GONE
                buttonBack.isClickable = true
                buttonLike.isClickable = true
            }
        }
    }

    companion object {
        private const val ID_HERO = "idHero"

        fun newInstance(id: String): HeroFragment {
            return HeroFragment().apply {

                arguments = bundleOf(ID_HERO to id)
            }
        }
    }
}
