package test.videolist.util

import androidx.recyclerview.widget.DiffUtil
import test.videolist.model.Comment

object DiffUtilComment: DiffUtil.ItemCallback<Comment>() {

    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.textOfComment == newItem.textOfComment
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }
}