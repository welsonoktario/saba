package id.saba.saba.data.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.saba.saba.R
import id.saba.saba.data.models.Forum

class ForumAdapter(private val context: Context, val data: ArrayList<Forum>) : RecyclerView.Adapter<ForumAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val headline: TextView = view.findViewById(R.id.txtForumHeadline)
        val deskripsi: TextView = view.findViewById(R.id.txtForumDeskripsi)
        val username: TextView = view.findViewById(R.id.txtForumUser)
        val tanggal: TextView = view.findViewById(R.id.txtForumWaktu)
        val upvote: TextView = view.findViewById(R.id.txtForumUpvote)
        val downvote: TextView = view.findViewById(R.id.txtForumDownvote)
        val viewer: TextView = view.findViewById(R.id.txtForumViewer)
        val comments: TextView = view.findViewById(R.id.txtForumComment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_forum, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val forum = data[position]

        holder.headline.text = forum.headline
        holder.deskripsi.text = forum.deskripsi
        holder.username.text = forum.userUsername()
        holder.tanggal.text = forum.postedTimeDiff()
        holder.deskripsi.text = forum.deskripsi
        holder.upvote.text = forum.upvote.toString()
        holder.downvote.text = forum.downvote.toString()
        holder.viewer.text = forum.viewer.toString()
        holder.comments.text = forum.commentCount().toString()
    }

    override fun getItemCount(): Int = data.size
}