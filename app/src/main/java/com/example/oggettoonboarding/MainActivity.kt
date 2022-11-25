package com.example.oggettoonboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.oggettoonboarding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val topLevelDestination = setOf(getSignInDestination())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        setSupportActionBar(binding.toolbar)
        val navController = findNavController(R.id.fragment_container_view)
        setupActionBarWithNavController(
            navController,
            AppBarConfiguration(topLevelDestination),
        )
        binding.toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }
    }

    private fun getSignInDestination() = R.id.signInFragment
}