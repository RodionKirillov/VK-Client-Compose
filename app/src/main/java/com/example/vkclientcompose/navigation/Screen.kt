package com.example.vkclientcompose.navigation

import android.net.Uri
import com.example.vkclientcompose.domain.entity.FeedPost
import com.google.gson.Gson

sealed class Screen(
    val route: String
) {
    object NewsFeed : Screen(route = ROUTE_NEWS_FEED)
    object FavoriteFeed : Screen(route = ROUTE_FAVORITE)
    object ProfileFeed : Screen(route = ROUTE_PROFILE)
    object Home : Screen(route = ROUTE_HOME)

    object Comments : Screen(route = ROUTE_COMMENTS) {

        private const val ROUTE_FOR_ARGS = "ROUTE_COMMENTS"

        fun getRouteWithArgs(feedPost: FeedPost): String {
            val feedPostJson = Gson().toJson(feedPost)
            return "$ROUTE_FOR_ARGS/${feedPostJson.encode()}"
        }
    }

    companion object {

        const val KEY_FEED_POST = "KEY_FEED_POST"

        const val ROUTE_NEWS_FEED = "ROUTE_NEWS_FEED"
        const val ROUTE_FAVORITE = "ROUTE_FAVORITE"
        const val ROUTE_PROFILE = "ROUTE_PROFILE"
        const val ROUTE_HOME = "ROUTE_HOME"
        const val ROUTE_COMMENTS = "ROUTE_COMMENTS/{$KEY_FEED_POST}"
    }

    fun String.encode(): String {
        return Uri.encode(this)
    }
}