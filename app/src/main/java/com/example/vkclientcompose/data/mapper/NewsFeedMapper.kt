package com.example.vkclientcompose.data.mapper

import com.example.vkclientcompose.data.model.CommentsResponseDto
import com.example.vkclientcompose.data.model.NewsFeedResponseDto
import com.example.vkclientcompose.domain.entity.FeedPost
import com.example.vkclientcompose.domain.entity.PostComment
import com.example.vkclientcompose.domain.entity.StatisticItem
import com.example.vkclientcompose.domain.entity.StatisticType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import kotlin.math.absoluteValue

class NewsFeedMapper @Inject constructor() {

    fun mapResponseToPost(responseDto: NewsFeedResponseDto): List<FeedPost> {
        val result = mutableListOf<FeedPost>()

        val posts = responseDto.newsFeedContent.posts
        val groups = responseDto.newsFeedContent.groups

        for (post in posts) {
            val group = groups.find { it.id == post.communityId.absoluteValue } ?: continue
            val feedPost = FeedPost(
                id = post.id,
                communityId = post.communityId,
                communityName = group.name,
                publicationData = mapTimestampToDate(post.date),
                communityImageUrl = group.imageUrl,
                contentText = post.text,
                contentImageUrl = post.attachments?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url,
                statistics = listOf(
                    StatisticItem(type = StatisticType.LIKES, post.likes.count),
                    StatisticItem(type = StatisticType.VIEWS, post.views.count),
                    StatisticItem(type = StatisticType.SHARES, post.reposts.count),
                    StatisticItem(type = StatisticType.COMMENTS, post.comments.count),
                ),
                isLiked = post.likes.userLikes > 0
            )
            result.add(feedPost)
        }
        return result
    }

    fun mapResponseToComments(responseDto: CommentsResponseDto): List<PostComment> {
        val result = mutableListOf<PostComment>()
        val comments = responseDto.content.comments
        val profiles = responseDto.content.profiles
        for (comment in comments) {
            if (comment.text.isBlank()) continue
            val author = profiles.firstOrNull { it.id == comment.authorId } ?: continue
            val postComment = PostComment(
                id = comment.id,
                authorName = "${author.firstName} ${author.lastName}",
                authorAvatarUrl = author.avatarUrl,
                commentText = comment.text,
                publicationData = mapTimestampToDate(comment.date)
            )
            result.add(postComment)
        }
        return result
    }

    private fun mapTimestampToDate(timestamp: Long): String {
        val date = Date(timestamp * 1000)
        return SimpleDateFormat("d MMMM yyyy, hh:mm", Locale.getDefault()).format(date)
    }
}