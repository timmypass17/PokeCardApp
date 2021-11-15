package com.example.pokecardapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHost = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHost.navController

        // Set multiple top level to remove extra back button. https://developer.android.com/guide/navigation/navigation-ui#appbarconfiguration
        appBarConfiguration = AppBarConfiguration(setOf(R.id.pokemonListFragment, R.id.pokemonSetListFragment, R.id.pokemonSearchFragment))

        setupActionBarWithNavController(navController, appBarConfiguration) // shows actionbar (back button)
        setupBottomNavMenu(navController) // bottom nav


    }

    override fun onSupportNavigateUp(): Boolean { // back button
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNav?.setupWithNavController(navController)
    }

}