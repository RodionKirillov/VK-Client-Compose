package com.example.vkclientcompose.domain

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.example.vkclientcompose.R
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedPost(
    val id: Int = 0,
    val communityName: String = "DEV NULL",
    val publicationData: String = "13:21",
    val avatarResId: Int = R.drawable.w_19082024_1,
    val contentText: String = "Если бы я создавал рейтинг Android-оболочек, то он бы выглядел так:",
    val contentImageResId: Int = R.drawable.pixel,
    val statistics: List<StatisticItem> = listOf(
        StatisticItem(type = StatisticType.VIEWS, 579),
        StatisticItem(type = StatisticType.SHARES, 15),
        StatisticItem(type = StatisticType.COMMENTS, 38),
        StatisticItem(type = StatisticType.LIKES, 309),
    )
): Parcelable {

    companion object {

        val NavigationType: NavType<FeedPost> = object : NavType<FeedPost>(false) {

            override fun get(bundle: Bundle, key: String): FeedPost? {
                return bundle.getParcelable(key)
            }

            override fun parseValue(value: String): FeedPost {
                return Gson().fromJson(value, FeedPost::class.java)
            }

            override fun put(bundle: Bundle, key: String, value: FeedPost) {
                bundle.putParcelable(key, value)
            }
        }
    }
}
