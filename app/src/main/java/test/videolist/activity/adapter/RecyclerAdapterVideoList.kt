package test.videolist.activity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import test.videolist.util.DiffUtilVideoItem
import extension.addImage
import test.videolist.R
import test.videolist.databinding.RecyclerViewVideoItemBinding
import test.videolist.model.VideoItem


class RecyclerAdapterVideoList(
    private val controllerListVideo: ControllerListVideo
) :
    ListAdapter<VideoItem, RecyclerAdapterVideoList.ViewHolder>(
        DiffUtilVideoItem
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_video_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            val videoItem = currentList[position]


            RecyclerViewVideoItemBinding.bind(itemView).run {
                ivPreview.addImage(videoItem.linkPreview)
                recyclerViewVideoItem.setOnClickListener {
                    controllerListVideo.openVideoInPlayer(videoItem)
                }
            }
        }
    }

    interface ControllerListVideo {
        fun openVideoInPlayer(video: VideoItem)
    }
}