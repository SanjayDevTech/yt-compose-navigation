package com.example.composenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composenavigation.screens.HomeSection
import com.example.composenavigation.screens.NotificationsSection
import com.example.composenavigation.screens.SettingsSection
import com.example.composenavigation.ui.theme.ComposeNavigationTheme

enum class Section(
    val route: String,
    val icon: ImageVector,
    val label: String,
) {
    HOME("home", Icons.Default.Home, "Home"),
    NOTIFICATIONS("notifications", Icons.Default.Notifications, "Notifications"),
    SETTINGS("settings", Icons.Default.Settings, "Settings"),
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigationTheme {
                val navController = rememberNavController()
                val currentDestination =
                    navController.currentBackStackEntryAsState().value?.destination
                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            for (section in Section.entries) {
                                val isSelected = currentDestination?.hierarchy?.any {
                                    it.route == section.route
                                } == true
                                NavigationBarItem(
                                    selected = isSelected,
                                    onClick = {
                                        navController.navigate(section.route) {
                                            popUpTo(navController.graph.findStartDestination().id)
                                            launchSingleTop = true
                                        }
                                    },
                                    label = {
                                        Text(text = section.label)
                                    },
                                    icon = {
                                        Icon(section.icon, contentDescription = section.label)
                                    })
                            }
                        }
                    }
                ) { paddingValues ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Section.HOME.route
                        ) {
                            composable(Section.HOME.route) {
                                HomeSection()
                            }
                            composable(Section.NOTIFICATIONS.route) {
                                NotificationsSection()
                            }
                            composable(Section.SETTINGS.route) {
                                SettingsSection()
                            }
                        }
                    }
                }
            }
        }
    }
}
