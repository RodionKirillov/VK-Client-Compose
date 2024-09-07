package com.example.vkclientcompose.navigation

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
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
                navArgument(Screen.KEY_FEED_POST) {
                    type = FeedPost.NavigationType
                }
            )
        ) { //ROUTE_NEWS_FEED/{feed_post_id}
            val args = it.arguments
            val feedPost = kotlin.runCatching {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                    args?.getParcelable(Screen.KEY_FEED_POST)
                } else {
                    args?.getParcelable(
                        Screen.KEY_FEED_POST,
                        FeedPost::class.java
                    )
                }
            }.getOrNull() ?: throw IllegalStateException()
            commentsScreenContent(feedPost)
        }
    }
}