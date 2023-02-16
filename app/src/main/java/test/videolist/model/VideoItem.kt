package test.videolist.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoItem(
    val name: String = "",
    val linkVideo: String = "",
    val linkPreview: String = "",
    val category: String = "",
    var inUserFavorite: Boolean = false,
    var rating: Float = 0.0F,
    var commentsList: List<Comment> = listOf()
) : Parcelable
