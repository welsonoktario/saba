package id.saba.saba.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.saba.saba.data.models.Forum
import id.saba.saba.databinding.CardForumBinding

class ForumAdapter(val data: ArrayList<Forum>, private val listener: OnForumClickListener) :
    RecyclerView.Adapter<ForumAdapter.ForumHolder>() {

    interface OnForumClickListener {
        fun onForumCardClickListener(position: Int)
    }

    inner class ForumHolder(private val binding: CardForumBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.cardForum.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onForumCardClickListener(absoluteAdapterPosition)
        }

        fun bind(forum: Forum) {
            binding.txtForumHeadline.text = forum.headline
            binding.txtForumDeskripsi.text = forum.deskripsi
            binding.txtForumUser.text = forum.userUsername()
            binding.txtForumWaktu.text = forum.postedTimeDiff()
            binding.txtForumUpvote.text = forum.upvote.toString()
            binding.txtForumDownvote.text = forum.downvote.toString()
            binding.txtForumViewer.text = forum.viewer.toString()
            binding.txtForumComment.text = forum.commentCount().toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForumHolder {
        val binding = CardForumBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ForumHolder(binding)
    }

    override fun onBindViewHolder(holder: ForumHolder, position: Int) = holder.bind(data[position])

    override fun getItemCount(): Int = data.size
}