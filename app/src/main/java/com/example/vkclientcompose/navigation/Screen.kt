package com.example.vkclientcompose.navigation

sealed class Screen(
    val route: String
) {
    object NewsFeed : Screen(route = ROUTE_NEWS_FEED)
    object FavoriteFeed : Screen(route = ROUTE_FAVORITE)
    object ProfileFeed : Screen(route = ROUTE_PROFILE)
    object Home : Screen(route = ROUTE_HOME)
    object Comments : Screen(route = ROUTE_COMMENTS)

    private companion object {

        const val ROUTE_NEWS_FEED = "ROUTE_NEWS_FEED"
        const val ROUTE_FAVORITE = "ROUTE_FAVORITE"
        const val ROUTE_PROFILE = "ROUTE_PROFILE"
        const val ROUTE_HOME = "ROUTE_HOME"
        const val ROUTE_COMMENTS = "ROUTE_COMMENTS"
    }
}