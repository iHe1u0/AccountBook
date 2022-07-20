package com.imorning.accountbook.activity

import android.os.Bundle
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
import com.imorning.accountbook.databinding.ActivityMainBinding
import com.imorning.accountbook.entity.DisburseData
import com.imorning.accountbook.entity.IncomeData
import com.imorning.accountbook.ui.disburse.DisburseViewModel
import com.imorning.accountbook.ui.disburse.DisburseViewModelFactory
import com.imorning.accountbook.viewmodels.DatabaseViewModel
import com.imorning.accountbook.viewmodels.DatabaseViewModelFactory
import java.sql.Date

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarconfigure: AppBarConfiguration

    private val databaseViewModel: DisburseViewModel by viewModels {
        DisburseViewModelFactory((application as App).database.bookDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.mainToolbar)

        binding.appBarMain.addButton.setOnClickListener {
            addNewData()
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
        navView.itemIconTintList = null

        navView.menu.findItem(R.id.nav_exit).setOnMenuItemClickListener {
            ActivityCollector.finish()
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarconfigure) || super.onSupportNavigateUp()
    }

    private fun addNewData() {
        databaseViewModel.insert(
            DisburseData(
                date = Date(System.currentTimeMillis()),
                type = "测试",
                remark = "测试标记",
                value = 100.0
            )
        )
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}