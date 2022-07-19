package com.imorning.accountbook.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.viewModels
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.imorning.accountbook.App
import com.imorning.accountbook.R
import com.imorning.accountbook.database.BookDatabase
import com.imorning.accountbook.databinding.ActivityMainBinding
import com.imorning.accountbook.entity.Book
import com.imorning.accountbook.viewmodels.DatabaseViewModel
import com.imorning.accountbook.viewmodels.DatabaseViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: BookDatabase
    private lateinit var appBarconfigure: AppBarConfiguration

    private val databaseViewModel: DatabaseViewModel by viewModels {
        DatabaseViewModelFactory((application as App).database.bookDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.mainToolbar)

        binding.appBarMain.addButton.setOnClickListener {
            MainScope().launch {
                addNewData()
            }
        }

        val drawerLayout: DrawerLayout = binding.mainDrawerLayout
        val navView: NavigationView = binding.navViewMain
        val fragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as Fragment
        val navController: NavController = fragment.findNavController()
        appBarconfigure = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_income, R.id.nav_disburse
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarconfigure)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarconfigure) || super.onSupportNavigateUp()
    }

    private suspend fun addNewData() = withContext(Dispatchers.IO) {
        databaseViewModel.insert(
            Book(
                id = 0,
                time = System.currentTimeMillis(),
                income = 100.0,
                balance = 100.0,
                disburse = 1.0,
                type = "测试",
                remark = "测试标记",
                isIncome = true
            )
        )
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}