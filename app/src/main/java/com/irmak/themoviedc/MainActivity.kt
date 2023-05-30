package com.irmak.themoviedc

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.irmak.themoviedc.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val toolbar = binding.toolbar
//        setSupportActionBar(toolbar)
//        if (supportActionBar != null) {
//            supportActionBar?.setTitle("Main Page")
//        }

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_fragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)
        val drawerLayout: DrawerLayout? = binding.drawerLayout
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.popularFragment, R.id.nowPlayingFragment, R.id.upcomingFragment),
            drawerLayout
        )
//        setupActionBar(navController)
        setupBottomNavMenu(navController)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNav?.setupWithNavController(navController)
    }

    private fun setupActionBar(
        navController: NavController
//        appBarConfig:AppBarConfiguration
    ) {
        setupActionBarWithNavController(navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    fun setBottomNavigationViewVisibility(visible: Boolean) {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        if (bottomNavigationView != null) {
            if (visible) {
                bottomNavigationView.visibility = View.VISIBLE
            } else {
                bottomNavigationView.visibility = View.GONE
            }
        }
    }
}