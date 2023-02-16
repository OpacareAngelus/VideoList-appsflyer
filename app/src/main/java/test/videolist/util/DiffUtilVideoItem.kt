package test.videolist.util

import androidx.recyclerview.widget.DiffUtil
import test.videolist.model.VideoItem

object DiffUtilVideoItem : DiffUtil.ItemCallback<VideoItem>() {

    override fun areItemsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
        return oldItem.linkVideo == newItem.linkVideo && oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
        return oldItem == newItem
    }
}