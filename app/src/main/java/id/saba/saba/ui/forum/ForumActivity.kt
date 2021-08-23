package id.saba.saba.ui.forum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import id.saba.saba.data.adapters.forum.ForumAdapter
import id.saba.saba.data.models.Comment
import id.saba.saba.data.models.Forum
import id.saba.saba.data.models.User
import id.saba.saba.databinding.ActivityForumBinding

class ForumActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForumBinding
    private lateinit var forums: ArrayList<Forum>
    private lateinit var adapter: ForumAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForumBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initView()
    }

    private fun initView() {
        forums = arrayListOf()
        for (i in 1..5) {
            forums.add(
                Forum(
                    i,
                    "Headline $i",
                    "Deskripsi $i",
                    "2021-08-15 20:13:53",
                    User(i, "User $i", "user$i@example.com"),
                    i * (1..3).random(),
                    i * (1..3).random(),
                    i * (1..3).random(),
                    arrayListOf(
                        Comment(
                            i,
                            "User $i",
                            "2021-08-15 22:11:03",
                            "Comment $i",
                            i * (1..3).random(),
                            i * (1..3).random()
                        )
                    )
                )
            )
        }
        adapter = ForumAdapter(this, forums)
        linearLayoutManager = LinearLayoutManager(this)
        binding.forumRV.adapter = adapter
        binding.forumRV.layoutManager = linearLayoutManager
    }
}