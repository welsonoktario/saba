package id.saba.saba.ui.forum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import id.saba.saba.data.adapters.ForumAdapter
import id.saba.saba.data.models.Comment
import id.saba.saba.data.models.Forum
import id.saba.saba.data.models.User
import id.saba.saba.databinding.ActivityForumBinding
import splitties.activities.start
import kotlin.collections.ArrayList

class ForumActivity : AppCompatActivity(), ForumAdapter.OnForumClickListener {
    private lateinit var forums: ArrayList<Forum>
    private lateinit var binding: ActivityForumBinding
    private lateinit var adapter: ForumAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        loadData()
    }

    private fun loadData() {
        forums.add(Forum(
            1,
            "Headline Forum 1",
            "Deskripsi forum 1",
            "2021-08-15 20:13:53",
            User(1, "User 1", "user1@example.com"),
            321,
            21,
            1234,
            arrayListOf(
                Comment(
                    1,
                    "User 2",
                    "2021-08-15 22:11:03",
                    "Comment 1",
                    123,
                    12
                )
            )
        ))
        adapter.notifyItemInserted(0)

        forums.add(Forum(
            2,
            "Headline Forum 2",
            "Deskripsi forum 2",
            "2021-08-15 22:11:35",
            User(2, "User 2", "user2@example.com"),
            234,
            23,
            2345,
            arrayListOf(
                Comment(
                    2,
                    "User 1",
                    "2021-08-15 23:21:06",
                    "Comment 2",
                    23,
                    2
                )
            )
        ))
        adapter.notifyItemInserted(1)

        forums = forums.reversed() as ArrayList<Forum>
    }

    private fun initView() {
        forums = arrayListOf()
        layoutManager = LinearLayoutManager(this)
        adapter = ForumAdapter(forums, this)
        binding.forumRV.adapter = adapter
        binding.forumRV.layoutManager = layoutManager

        binding.actionButton.setOnClickListener { addForum() }
        binding.btnBack.setOnClickListener { finish() }
    }

    private fun addForum() {
        val forum = Forum(
            3,
            "Headline Forum 3",
            "Deskripsi forum 3",
            "2021-08-17 23:21:06",
            User(1, "User 1", "user1@example.com"),
            234,
            23,
            2345,
            arrayListOf()
        )
        forums.add(0, forum)
        adapter.notifyItemInserted(0)
        layoutManager.smoothScrollToPosition(binding.forumRV, null, 0)
    }

    override fun onItemClickListener(position: Int) {
        start<DetailForumActivity> { this.putExtra("FORUM", forums[position]) }
    }
}