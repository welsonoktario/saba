package id.saba.saba.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import id.saba.saba.ClassNews
import id.saba.saba.ui.news.DetailNewsActivity
import id.saba.saba.ui.adapters.NewsAdapter
import id.saba.saba.R
import kotlinx.android.synthetic.main.fragment_news.view.*
import splitties.fragments.start
import splitties.toast.toast

class NewsFragment : Fragment() {
    private lateinit var adapter: NewsAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var news: ArrayList<ClassNews>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayNews(view)
    }

    private fun displayNews(root: View) {
        news = arrayListOf(
            ClassNews(
                "Pengguna A",
                "Berita 1",
                "https://picsum.photos/200/150",
                "11-01-2001",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
            ),
            ClassNews(
                "Pengguna B",
                "Berita 2",
                "https://picsum.photos/200/150",
                "11-01-2001",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
            ),
            ClassNews(
                "Pengguna C",
                "Berita 3",
                "https://picsum.photos/200/150",
                "11-01-2001",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
            )
        )

        layoutManager = LinearLayoutManager(requireContext())
        adapter = NewsAdapter(news) { n ->
            run {
                toast(n.judul)
                start<DetailNewsActivity>()
            }
        }

        root.newsRV.layoutManager = layoutManager
        root.newsRV.adapter = adapter
    }
}