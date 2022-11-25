package com.example.oggettoonboarding.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.oggettoonboarding.R
import com.example.oggettoonboarding.auth.models.AuthState
import com.example.oggettoonboarding.databinding.ActivityMainBinding
import com.example.oggettoonboarding.fragments.tabs.TabsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel by viewModels<MainViewModel> { MainViewModelFactory() }
    private val topLevelDestination = setOf(getSignInDestination(), getTabsDestination())

    private val destinationListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.title = destination.label
            supportActionBar?.setDisplayHomeAsUpEnabled(!isStartDestination(destination))
        }

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?,
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            if (f is TabsFragment || f is NavHostFragment) return
            onNavControllerActivated(f.findNavController())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        setSupportActionBar(binding.toolbar)
        navController = getRootNavController()
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        observeViewModel(navController)
        onNavControllerActivated(navController)
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, true)
        setupActionBarWithNavController(
            navController,
            AppBarConfiguration(topLevelDestination)
        )
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

    private fun observeViewModel(navController: NavController) {
        val graph = navController.navInflater.inflate(getMainNavGraph())
        viewModel.userState.observe(this) { authState ->
            when (authState) {
                is AuthState.Success -> {
                    graph.setStartDestination(getTabsDestination())
                    navController.graph = graph
                }
                is AuthState.Error -> {
                    graph.setStartDestination(getSignInDestination())
                    navController.graph = graph
                }
            }
        }
    }

    private fun getRootNavController(): NavController {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        return navHost.navController
    }

    override fun onSupportNavigateUp(): Boolean =
        (navController.navigateUp()) || super.onSupportNavigateUp()

    private fun onNavControllerActivated(navController: NavController) {
        if (this.navController == navController) return
        this.navController.removeOnDestinationChangedListener(destinationListener)
        navController.addOnDestinationChangedListener(destinationListener)
        this.navController = navController
    }

    private fun isStartDestination(destination: NavDestination?): Boolean {
        if (destination == null) return false
        val graph = destination.parent ?: return false
        val startDestinations = topLevelDestination + graph.startDestinationId
        return startDestinations.contains(destination.id)
    }

    private fun getSignInDestination() = R.id.signInFragment

    private fun getTabsDestination() = R.id.tabsFragment

    private fun getMainNavGraph() = R.navigation.main_graph

    override fun onDestroy() {
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
        super.onDestroy()
    }
}