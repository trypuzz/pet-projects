package com.example.notes.ui.viewpagefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.notes.databinding.FragmentPageBinding

private const val IMAGE_EXTRA = "image extra"
private const val TEXT_EXTRA = "text extra"

class PageFragment : Fragment() {

    private lateinit var binding: FragmentPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(IMAGE_EXTRA)?.let { binding.imagePage.setImageResource(it) }
        binding.textPage.text = arguments?.getString(TEXT_EXTRA)
    }

    companion object {
        fun getPageFragmentInstance(id: Int, text: String): PageFragment {
            return PageFragment().apply {
                arguments = bundleOf(
                    IMAGE_EXTRA to id,
                    TEXT_EXTRA to text
                )
            }
        }
    }
}