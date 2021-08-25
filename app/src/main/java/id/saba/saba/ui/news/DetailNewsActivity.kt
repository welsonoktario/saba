package id.saba.saba.ui.news

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import id.saba.saba.R
import id.saba.saba.data.models.News
import id.saba.saba.databinding.ActivityDetailNewsBinding

class DetailNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailNewsBinding
    private lateinit var news: News

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        initView()
    }

    private fun loadData() {
        news = intent.getParcelableExtra("NEWS")!!
    }

    private fun initView() {
        binding.txtDetailNewsHeadline.text = news.judul
        binding.txtDetailNewsUser.text = news.user.username
        binding.txtDetailNewsWaktu.text = news.tanggal
        binding.txtDetailNewsDeskripsi.text = news.berita

        if (news.bookmarked) {
            binding.imgDetailNewsBookmark.setColorFilter(ContextCompat.getColor(this, R.color.primary), PorterDuff.Mode.SRC_IN)
        }
    }
}