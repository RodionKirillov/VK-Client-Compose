package com.example.vkclientcompose.domain

import com.example.vkclientcompose.R

data class FeedPost(
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
)
