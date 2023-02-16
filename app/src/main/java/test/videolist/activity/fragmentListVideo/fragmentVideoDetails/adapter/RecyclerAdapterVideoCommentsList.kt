package test.videolist.activity.fragmentListVideo.fragmentVideoDetails.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import test.videolist.R
import test.videolist.activity.fragmentListVideo.fragmentVideoDetails.FragmentVideoDetails
import test.videolist.databinding.RecyclerViewVideoCommentItemBinding
import test.videolist.model.Comment
import test.videolist.util.DiffUtilComment

class RecyclerAdapterVideoCommentsList() :
    ListAdapter<Comment, RecyclerAdapterVideoCommentsList.ViewHolder>(
        DiffUtilComment
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_video_comment_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterVideoCommentsList.ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            RecyclerViewVideoCommentItemBinding.bind(itemView).run {
                tvCommentText.text = currentList[position].textOfComment
            }
        }
    }
}