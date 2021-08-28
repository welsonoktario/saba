package id.saba.saba.ui.tabs.home

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.saba.saba.data.adapters.SliderAdapter
import com.smarteist.autoimageslider.SliderView
import id.saba.saba.R
import id.saba.saba.SliderModal
import id.saba.saba.data.adapters.EventAdapter
import id.saba.saba.data.adapters.ForumAdapter
import id.saba.saba.data.adapters.NewsAdapter
import id.saba.saba.data.models.*
import id.saba.saba.databinding.FragmentHomeBinding
import id.saba.saba.ui.event.DetailEventActivity
import id.saba.saba.ui.forum.DetailForumActivity
import id.saba.saba.ui.news.DetailNewsActivity
import org.json.JSONArray
import splitties.fragments.start
import kotlin.collections.ArrayList

class HomeFragment : Fragment(), ForumAdapter.OnForumClickListener,
    EventAdapter.OnEventClickListener, NewsAdapter.OnNewsClickListener {
    private var _binding: FragmentHomeBinding? = null
    private lateinit var viewPager: SliderView
    private lateinit var adapterSlider: SliderAdapter
    private lateinit var sliderModalArrayList: ArrayList<SliderModal>
    private lateinit var forums: ArrayList<Forum>
    private lateinit var events: ArrayList<Event>
    private lateinit var news: ArrayList<News>
    private lateinit var adapterForum: ForumAdapter
    private lateinit var adapterEvent: EventAdapter
    private lateinit var adapterNews: NewsAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        loadData()
    }

    private fun loadData() {
        // api call item slider, setelah dapat response, jalankan dibawah
        viewPager = binding.idViewPager
        sliderModalArrayList = arrayListOf(
            SliderModal("Langkah 1", "Deskipsi 1", R.drawable.ic_launcher_background),
            SliderModal("Langkah 2", "Deskipsi 2", R.drawable.ic_launcher_background),
            SliderModal("Langkah 3", "Deskipsi 3", R.drawable.ic_launcher_background)
        )
        adapterSlider = SliderAdapter(requireContext(), sliderModalArrayList)
        viewPager.setSliderAdapter(adapterSlider)
        viewPager.scrollTimeInSec = 3
        viewPager.isAutoCycle = true

        // dummy
        loadForum()
        loadNews()
        loadEvent()
    }

    private fun initViews() {
        initEvents()
        initNews()
        initTrendingForums()

        val inputMethodManager =
            requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        binding.parentLayout.setOnClickListener {
            binding.inputSearch.clearFocus()
            inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)
        }
    }

    private fun initTrendingForums() {
        forums = arrayListOf()
        adapterForum = ForumAdapter(forums, this)
        binding.homeTrendingForumRV.adapter = adapterForum
        binding.homeTrendingForumRV.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initEvents() {
        events = arrayListOf()
        adapterEvent = EventAdapter(events, this)
        binding.homeEventRV.adapter = adapterEvent
        binding.homeEventRV.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initNews() {
        news = arrayListOf()
        adapterNews = NewsAdapter(news, this)
        binding.homeNewsRV.adapter = adapterNews
        binding.homeNewsRV.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun loadDataRV(tipe: String, data: JSONArray) {
        /*when (tipe) {
            "forum" -> for (i in 0 until data.length()) {
                val obj = data.getJSONObject(i)
                val comments = obj.getJSONArray("comment")
                val forum = Forum(
                    obj.getInt("id"),
                    obj.getString("headline"),
                    obj.getString("deskripsi"),
                    obj.getString("tanggal"),
                    User(
                        obj.getInt("user_id"),
                        obj.getString("user_username"),
                        obj.getString("user_email")
                    ),
                    obj.getInt("upvote"),
                    obj.getInt("downvote"),
                    obj.getInt("viewer"),
                    arrayListOf()
                )

                for (j in 0 until comments.length()) {
                    val objC = comments.getJSONObject(j)
                    val comment = Comment(
                        objC.getInt("id"),
                        objC.getString("username"),
                        objC.getString("tanggal"),
                        objC.getString("comment"),
                        objC.getInt("upvote"),
                        objC.getInt("downvote")
                    )
                    forum.comments.add(comment)
                }

                forums.add(forum)
                adapterForum.notifyItemInserted(i)
            }
            // dst untuk event dan news
        }*/
    }

    // dummy
    private fun loadNews() {
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
        adapterNews.notifyItemInserted(0)

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
        adapterNews.notifyItemInserted(1)

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
        adapterNews.notifyItemInserted(2)
        news = news.reversed() as ArrayList<News>
    }

    // dummy
    private fun loadForum() {
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
        adapterForum.notifyItemInserted(0)

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
        adapterForum.notifyItemInserted(1)

        forums = forums.reversed() as ArrayList<Forum>
    }

    // dummy
    private fun loadEvent() {
        // pas dapat response dari volley, response di loop lalu array event kosong tadi ditambah
        events.add(Event(
            1,
            "https://picsum.photos/200/300",
            "Music Concert",
            "Headline Music Concert 1",
            User(1, "User 1", "user1@example.com"),
            "Deskripsi music concert 1",
            "Universitas Ciputra, Jawa Timur, Surabaya",
            "2021-08-24",
            "2021-08-27",
            12,
            15,
            250000,
            350000
        ))
        adapterEvent.notifyItemInserted(0) // pakai index dari loop

        events.add(Event(
            2,
            "https://picsum.photos/200/300",
            "Music Concert",
            "Headline Music Concert 2",
            User(2, "User 2", "user2@example.com"),
            "Deskripsi music concert 2",
            "Universitas Surabaya, Jawa Timur, Surabaya",
            "2021-08-20",
            "2021-08-25",
            15,
            18,
            200000,
            250000
        ))
        adapterEvent.notifyItemInserted(1)

        // reverse array untuk menampilkan data terbaru (dihapus kalau dari server udah diurut)
        events = events.reversed() as ArrayList<Event>
    }

    override fun onEventCardClickListener(position: Int) {
        start<DetailEventActivity> { this.putExtra("EVENT", events[position]) }
    }

    override fun onForumCardClickListener(position: Int) {
        start<DetailForumActivity> { this.putExtra("FORUM", forums[position]) }
    }

    override fun onNewsCardClickListener(position: Int) {
        start<DetailNewsActivity> { this.putExtra("NEWS", news[position]) }
    }
}