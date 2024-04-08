package com.animated_anand.moengagenews.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.animated_anand.moengagenews.R
import com.animated_anand.moengagenews.databinding.FragmentBookmarksBinding
import com.animated_anand.moengagenews.databinding.FragmentPreferencesBinding

class PreferencesFragment : Fragment() {

    private lateinit var binding: FragmentPreferencesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPreferencesBinding.inflate(layoutInflater)
        return binding.root
    }
}