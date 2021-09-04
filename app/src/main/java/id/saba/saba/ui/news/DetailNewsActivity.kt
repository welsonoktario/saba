package id.saba.saba.ui.news

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat
import id.saba.saba.R
import id.saba.saba.data.models.News
import id.saba.saba.databinding.ActivityDetailNewsBinding

class DetailNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailNewsBinding
    private lateinit var news: News
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
    }

    private fun loadData() {
        news = intent.getParcelableExtra("NEWS")!!
        initView()
    }

    private fun initView() {
        webView = binding.webViewDetailNewsContent
        binding.txtDetailNewsHeadline.text = news.judul
        binding.txtDetailNewsUser.text = news.user.username
        binding.txtDetailNewsWaktu.text = news.tanggal

        if (news.bookmarked) {
            binding.imgDetailNewsBookmark.setColorFilter(ContextCompat.getColor(this, R.color.primary), PorterDuff.Mode.SRC_IN)
        }

        webView.loadDataWithBaseURL("file:///android_asset/www", news.html(), "text/html; charset=utf-8", "UTF-8", null)
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                if (webView.progress == 100) {
                    view.requestLayout()
                }
            }
        }
        webView.settings.javaScriptEnabled = true

        binding.btnBack.setOnClickListener { finish() }
    }
}