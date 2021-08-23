package id.saba.saba.ui.forum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import id.saba.saba.R
import id.saba.saba.data.models.Forum

class DetailForumActivity : AppCompatActivity() {
    private lateinit var forum: Forum

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_forum)
        forum = intent.getParcelableExtra<Forum>("FORUM")!!
        Log.d("FORUM", forum.toString())
    }

}