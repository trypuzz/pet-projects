package com.example.notes.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notes.R
import com.example.notes.constance.Constance
import com.example.notes.databinding.FragmentFindProjectBinding

class FindProjectFragment_2 : Fragment() {

    private lateinit var binding: FragmentFindProjectBinding
    val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFindProjectBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSkip2.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, MakeMoneyFragment_3())
                .addToBackStack("")
                .commit()
            handler.removeCallbacksAndMessages(null);
        }

        handler.postDelayed({
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, MakeMoneyFragment_3())
                .addToBackStack("")
                .commit()
        }, Constance.TIME_SLIDE_ACTIVITY)
    }

}