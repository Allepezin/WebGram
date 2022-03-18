package com.browser.webgram.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.browser.webgram.R
import com.browser.webgram.constants.APP_ACTIVITY
import com.browser.webgram.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        APP_ACTIVITY = this
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }
}
