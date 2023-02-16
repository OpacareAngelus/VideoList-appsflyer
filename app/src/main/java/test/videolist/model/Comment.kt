package test.videolist.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comment(
    val textOfComment: String = "",
    val like: Int = 0,
    val dislike: Int = 0,
    val authorId : String = ""
) : Parcelable