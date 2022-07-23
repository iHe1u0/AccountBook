package com.imorning.accountbook.activity

import android.content.Intent
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
import com.imorning.accountbook.entity.IncomeRecordEntity
import com.imorning.accountbook.ui.income.IncomeViewModel
import com.imorning.accountbook.ui.income.IncomeViewModelFactory
import java.sql.Date
import kotlin.random.Random

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfigure: AppBarConfiguration

    private val databaseViewModel: IncomeViewModel by viewModels {
        IncomeViewModelFactory((application as App).database.bookDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.mainToolbar)

        binding.appBarMain.addButton.setOnClickListener {
            // DatabaseUtils.changePassword(this, "password", "123")
            repeat(3) {
                addNewData()
            }
        }

        val drawerLayout: DrawerLayout = binding.mainDrawerLayout
        val navView: NavigationView = binding.navViewMain
        val fragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as Fragment
        val navController: NavController = fragment.findNavController()
        appBarConfigure = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_income, R.id.nav_disburse
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfigure)
        navView.setupWithNavController(navController)
        navView.itemIconTintList = null

        navView.menu.apply {
            findItem(R.id.nav_settings).setOnMenuItemClickListener {
                val intent: Intent = Intent(this@MainActivity, SettingsActivity::class.java)
                startActivity(intent)
                // this@MainActivity.finish()
                true
            }
            findItem(R.id.nav_exit).setOnMenuItemClickListener {
                ActivityCollector.finish()
                true
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfigure) || super.onSupportNavigateUp()
    }

    private fun addNewData() {
        databaseViewModel.insert(
            IncomeRecordEntity(
                date = Date(System.currentTimeMillis()),
                type = "测试-${Random(1000).nextDouble()}",
                remark = "测试标记",
                value = 100.0
            )
        )
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}