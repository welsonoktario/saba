package com.example.sabaid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sabaid.*
import com.example.sabaid.ui.home.fragments.EventFragment
import com.example.sabaid.ui.home.fragments.ForumFragment
import com.example.sabaid.ui.home.fragments.NewsFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {
    private lateinit var viewPager: SliderView
    private lateinit var adapter: SliderAdapter
    private lateinit var sliderModalArrayList: ArrayList<SliderModal>
    private lateinit var homeFragmentAdapter: FragmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayEventHighlight(view)
        displayTabLayout(view)
    }

    private fun displayTabLayout(v: View) {
        val fragments = arrayListOf(NewsFragment(), ForumFragment(), EventFragment())
        homeFragmentAdapter = FragmentAdapter(this, fragments)
        val tabLayout = v.tabLayout
        val homeVP = v.homeVP

        homeVP.adapter = homeFragmentAdapter
        TabLayoutMediator(tabLayout, homeVP) { tab, position ->
            when (position) {
                0 -> tab.text = "News"
                1 -> tab.text = "Forum"
                2 -> tab.text = "Event"
            }
        }.attach()
    }

/*    private fun displayTrendingForum(root: View) {
        forums = arrayListOf(
            ClassTrendingForum(
                "Pengguna A",
                "11-01-2001",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
            ),
            ClassTrendingForum(
                "Pengguna B",
                "11-01-2001",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
            ),
            ClassTrendingForum(
                "Pengguna C",
                "11-01-2001",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
            )
        )
        val forumsAdapter = TrendingForumAdapter(forums.reversed()) { forums ->
            run {
                toast(forums.name)
                start<DetailNewsActivity>()
            }
        }
        root.recyclerViewTrendingForum_1.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = forumsAdapter
        }
    }*/

    private fun displayEventHighlight(v: View) {
        /*events = arrayOf(ClassEvent("Promosi", "1"), ClassEvent("Promosi", "2"),
            ClassEvent("Promosi", "3"))
        val eventsAdapter = EventAdapter(events.reversed()){
            events -> run {
                toast(events.number)
            }
        }
        root.recyclerViewEventKampus_1.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = eventsAdapter
        }*/
        viewPager = v.idViewPager
        sliderModalArrayList = arrayListOf(
            SliderModal("Langkah 1", "Deskipsi 1", R.drawable.ic_launcher_background),
            SliderModal("Langkah 2", "Deskipsi 2", R.drawable.ic_launcher_background),
            SliderModal("Langkah 3", "Deskipsi 3", R.drawable.ic_launcher_background)
        )
        adapter = SliderAdapter(requireContext(), sliderModalArrayList)
        viewPager.setSliderAdapter(adapter)
        viewPager.scrollTimeInSec = 3
        viewPager.isAutoCycle = true
    }

    override fun onDetach() {
        super.onDetach()
        viewPager.isAutoCycle = false
    }

    override fun onDestroy() {
        super.onDestroy()
        viewPager.isAutoCycle = false
    }

    override fun onStop() {
        super.onStop()
        viewPager.isAutoCycle = false
    }

    override fun onResume() {
        super.onResume()
        viewPager.isAutoCycle = true
    }
}