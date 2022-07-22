package com.imorning.accountbook.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.imorning.accountbook.view.OverviewTheme

private const val TAG = "PrimaryActivity"

class PrimaryActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OverviewTheme {
                MainScreen()
            }
        }
    }

    @Preview
    @Composable
    fun MainScreen() {
        Button(onClick = { /*TODO*/ }) {
            Text(text = "主界面")
        }
    }
}