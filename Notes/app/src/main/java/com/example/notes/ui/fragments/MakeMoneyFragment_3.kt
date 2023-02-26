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
import com.example.notes.databinding.FragmentMakeMoneyBinding



class MakeMoneyFragment_3 : Fragment() {

    private lateinit var binding: FragmentMakeMoneyBinding
    val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMakeMoneyBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSkip3.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, ChatFragment_4())
                .addToBackStack("")
                .commit()
            handler.removeCallbacksAndMessages(null);
        }

        handler.postDelayed({
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, ChatFragment_4())
                .addToBackStack("")
                .commit()
        }, Constance.TIME_SLIDE_ACTIVITY)
    }
}


