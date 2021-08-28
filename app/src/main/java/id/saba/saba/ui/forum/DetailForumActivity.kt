package id.saba.saba.ui.forum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import id.saba.saba.data.adapters.CommentAdapter
import id.saba.saba.data.models.Comment
import id.saba.saba.data.models.Forum
import id.saba.saba.databinding.ActivityDetailForumBinding

class DetailForumActivity : AppCompatActivity(), CommentAdapter.OnCommentClickListener {
    private lateinit var binding: ActivityDetailForumBinding
    private lateinit var forum: Forum
    private lateinit var comments: ArrayList<Comment>
    private lateinit var adapter: CommentAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailForumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadForum()
    }

    private fun loadForum() {
        // api call
        /*forum = Forum(
            id,
            "Headline Forum 3",
            "Deskripsi forum 3",
            "2021-08-17 23:21:06",
            User(1, "User 1", "user1@example.com"),
            234,
            23,
            2345,
            arrayListOf()
        )*/
        forum = intent.getParcelableExtra("FORUM")!!
        comments = forum.comments

        initView()
    }

    private fun initView() {
        binding.txtDetailForumHeadline.text = forum.headline
        binding.txtDetailForumUser.text = forum.userUsername()
        binding.txtDetailForumWaktu.text = forum.tanggal
        binding.txtDetailForumDeskripsi.text = forum.deskripsi

        adapter = CommentAdapter(comments, this)
        layoutManager = LinearLayoutManager(this)
        binding.forumCommentRV.adapter = adapter
        binding.forumCommentRV.layoutManager = layoutManager

        binding.btnBack.setOnClickListener { finish() }
    }

    private fun addComment(comment: Comment) {
        comments.add(comment)
        adapter.notifyItemInserted(0)
    }

    override fun onVoteComment(position: Int, tipe: String) {
        when (tipe) {
            "upvote" -> comments[position].upvote++
            "downvote" -> comments[position].downvote++
        }
        adapter.notifyItemChanged(position)
    }
}