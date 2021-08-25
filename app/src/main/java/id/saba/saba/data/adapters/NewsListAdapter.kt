package id.saba.saba.data.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.saba.saba.ui.news.fragments.NewsListFragment

class NewsListAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): NewsListFragment {
        val fragment = NewsListFragment()
        val bundle = Bundle()

        when (position) {
            0 -> bundle.putString("NEWS_TYPE", "latest")
            1 -> bundle.putString("NEWS_TYPE", "popular")
        }

        fragment.arguments = bundle

        return fragment
    }
}