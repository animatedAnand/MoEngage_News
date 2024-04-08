package com.animated_anand.moengagenews.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.animated_anand.moengagenews.R
import com.animated_anand.moengagenews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binds nav_graph components to the frame layout of main activity
        NavigationUI.setupWithNavController(binding.bnMenu, Navigation.findNavController(this,R.id.fr_main))
    }
}