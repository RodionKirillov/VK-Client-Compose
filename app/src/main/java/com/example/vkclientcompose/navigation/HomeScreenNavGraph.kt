package com.example.vkclientcompose.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.vkclientcompose.domain.FeedPost

fun NavGraphBuilder.homeScreenNavGraph(
    commentsScreenContent: @Composable (FeedPost) -> Unit,
    newsFeedScreenContent: @Composable () -> Unit,
) {
    navigation(
        startDestination = Screen.NewsFeed.route,
        route = Screen.Home.route
    ) {
        composable(Screen.NewsFeed.route) {
            newsFeedScreenContent()
        }
        composable(
            route = Screen.Comments.route,
            arguments = listOf(
                navArgument(Screen.KEY_FEED_POST_ID) {
                    type = NavType.IntType
                },

                navArgument(Screen.KEY_FEED_POST_CONTENT) {
                    type = NavType.StringType
                }
            )
        ) { //ROUTE_NEWS_FEED/{feed_post_id}
            val feedPostId = it.arguments?.getInt(Screen.KEY_FEED_POST_ID) ?: 0
            val feedPostContent = it.arguments?.getString(Screen.KEY_FEED_POST_CONTENT) ?: ""
            commentsScreenContent(
                FeedPost(
                    id = feedPostId,
                    contentText = feedPostContent
                )
            )
        }
    }
}