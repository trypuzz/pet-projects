package com.example.notes.ui.screeens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notes.R
import com.example.notes.databinding.FragmentStartAppBinding
import com.example.notes.ui.screeens.registration.LogInFragment
import com.example.notes.ui.viewpagefragment.ViewPageFragment

class StartAppFragment : Fragment() {

    private lateinit var binding: FragmentStartAppBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartAppBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonStart.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, ViewPageFragment())
                .addToBackStack("")
                .commit()
        }
        binding.btnLoginLauncher.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, LogInFragment())
                .addToBackStack("")
                .commit()
        }
    }
}
