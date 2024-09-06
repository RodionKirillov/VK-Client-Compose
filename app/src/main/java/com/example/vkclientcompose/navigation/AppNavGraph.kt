package com.example.vkclientcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    favoriteScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable () -> Unit,
    newsFeedScreenContent: @Composable () -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
    ) {
        homeScreenNavGraph(
            commentsScreenContent = commentsScreenContent,
            newsFeedScreenContent = newsFeedScreenContent,
        )

        composable(Screen.FavoriteFeed.route) {
            favoriteScreenContent()
        }

        composable(Screen.ProfileFeed.route) {
            profileScreenContent()
        }
    }
}