package id.saba.saba.data.adapters.forum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.saba.saba.R
import id.saba.saba.data.models.Comment

class ForumCommentAdapter(val data: ArrayList<Comment>) :
    RecyclerView.Adapter<ForumCommentAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val user: TextView = view.findViewById(R.id.txtCommentUser)
        val waktu: TextView = view.findViewById(R.id.txtCommentWaktu)
        val comment: TextView = view.findViewById(R.id.txtCommentComment)
        val upvote: TextView = view.findViewById(R.id.txtCommentUpvote)
        val downvote: TextView = view.findViewById(R.id.txtCommentDownvote)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_forum_comment, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = data[position]

        holder.user.text = comment.user
        holder.waktu.text = comment.postedTimeDiff()
        holder.comment.text = comment.comment
        holder.upvote.text = comment.upvote.toString()
        holder.downvote.text = comment.downvote.toString()
    }

    override fun getItemCount(): Int = data.size
}