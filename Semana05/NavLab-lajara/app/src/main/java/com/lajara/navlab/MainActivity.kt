package com.lajara.navlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.lajara.navlab.navigation.AppNavigation
import com.lajara.navlab.ui.theme.NavLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavLabTheme {
                AppNavigation()
            }
        }
    }
}
