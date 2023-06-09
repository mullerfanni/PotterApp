package com.example.potterapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.potterapp.theme.CustomTheme
import com.example.potterapp.ui.background.HogwartsBackground
import com.example.potterapp.ui.navigation.LocalNavController
import com.example.potterapp.ui.navigation.Navigation
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var analytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        // Obtain the FirebaseAnalytics instance.
        analytics = Firebase.analytics
        setContent {
            CustomTheme {
                CompositionLocalProvider(
                    LocalNavController provides rememberNavController()
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        HogwartsBackground()
                        Navigation(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Transparent)
                                .statusBarsPadding()
                                .navigationBarsPadding()
                        )
                    }

                }
            }
        }
    }
}
