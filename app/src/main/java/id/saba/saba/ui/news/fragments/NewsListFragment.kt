package id.saba.saba.ui.news.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import id.saba.saba.data.adapters.NewsAdapter
import id.saba.saba.data.models.News
import id.saba.saba.data.models.User
import id.saba.saba.databinding.FragmentNewsListBinding
import id.saba.saba.ui.news.DetailNewsActivity
import splitties.fragments.start

class NewsListFragment : Fragment(), NewsAdapter.OnNewsClickListener {
    private var _binding: FragmentNewsListBinding? = null
    private lateinit var adapter: NewsAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var news: ArrayList<News>
    private lateinit var tipe: String

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        tipe = arguments?.getString("NEWS_TYPE")!!
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun loadData(tipeNews: String) {
        if (tipeNews === "popular") {
            news.add(News(
                1,
                User(1, "User 1", "user1@example.com"),
                "Berita Populer 1",
                "https://picsum.photos/200/150",
                "11-01-2001",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
                        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim " +
                        "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                false
            ))
            adapter.notifyItemInserted(0)

            news.add(News(
                2,
                User(1, "User 1", "user1@example.com"),
                "Berita Populer 2",
                "https://picsum.photos/200/150",
                "11-01-2001",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
                        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim " +
                        "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                false
            ))
            adapter.notifyItemInserted(1)

            news.add(News(
                3,
                User(2, "User 2", "user2@example.com"),
                "Berita Populer 3",
                "https://picsum.photos/200/150",
                "11-01-2001",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
                        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim " +
                        "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                true
            ))
            adapter.notifyItemInserted(2)
        } else {
            // api call berita terbaru
            news.add(News(
                1,
                User(1, "User 1", "user1@example.com"),
                "Berita Terbaru 1",
                "https://picsum.photos/200/150",
                "11-01-2001",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
                        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim " +
                        "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                true
            ))
            adapter.notifyItemInserted(0)

            news.add(News(
                2,
                User(1, "User 1", "user1@example.com"),
                "Berita Terbaru 2",
                "https://picsum.photos/200/150",
                "11-01-2001",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
                        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim " +
                        "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                false
            ))
            adapter.notifyItemInserted(1)

            news.add(News(
                3,
                User(2, "User 2", "user2@example.com"),
                "Berita Terbaru 3",
                "https://picsum.photos/200/150",
                "11-01-2001",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
                        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim " +
                        "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                true
            ))
            adapter.notifyItemInserted(2)
        }
        news = news.reversed() as ArrayList<News>
    }

    private fun initView() {
        news = arrayListOf()
        adapter = NewsAdapter(news, this)
        layoutManager = LinearLayoutManager(requireContext())
        binding.newsRV.adapter = adapter
        binding.newsRV.layoutManager = layoutManager

        loadData(tipe)
    }

    override fun onItemClickListener(position: Int) {
        start<DetailNewsActivity> { this.putExtra("NEWS", news[position]) }
    }
}