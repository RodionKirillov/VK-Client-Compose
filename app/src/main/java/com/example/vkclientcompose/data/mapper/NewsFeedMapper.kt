package com.example.vkclientcompose.data.mapper

import com.example.vkclientcompose.data.model.NewsFeedResponseDto
import com.example.vkclientcompose.domain.FeedPost
import com.example.vkclientcompose.domain.StatisticItem
import com.example.vkclientcompose.domain.StatisticType
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.absoluteValue

class NewsFeedMapper {

    fun mapResponseToPost(responseDto: NewsFeedResponseDto): List<FeedPost> {
        val result = mutableListOf<FeedPost>()

        val posts = responseDto.newsFeedContent.posts
        val groups = responseDto.newsFeedContent.groups

        for (post in posts) {
            val group = groups.find { it.id == post.communityId.absoluteValue } ?: continue
            val feedPost = FeedPost(
                id = post.id,
                communityName = group.name,
                publicationData = mapTimestampToDate(post.date * 1000),
                communityImageUrl = group.imageUrl,
                contentText = post.text,
                contentImageUrl = post.attachments?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url,
                statistics = listOf(
                    StatisticItem(type = StatisticType.LIKES, post.likes.count),
                    StatisticItem(type = StatisticType.VIEWS, post.views.count),
                    StatisticItem(type = StatisticType.SHARES, post.reposts.count),
                    StatisticItem(type = StatisticType.COMMENTS, post.comments.count),
                ),
                isFavorite = post.isFavorite
            )
            result.add(feedPost)
        }
        return result
    }

    private fun mapTimestampToDate(timestamp: Long): String {
        val date = Date(timestamp)
        return SimpleDateFormat("d MMMM yyyy, hh:mm", Locale.getDefault()).format(date)

    }
}