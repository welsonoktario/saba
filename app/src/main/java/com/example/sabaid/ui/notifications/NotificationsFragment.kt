package com.example.sabaid.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sabaid.R

class NotificationsFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
       return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    /*fun displayNotifikasi(root: View) {
        notifikasi = arrayOf(
            ClassNotifikasi(
                "1",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "12",
                R.drawable.ic_launcher_background
            ),
            ClassNotifikasi(
                "2",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "12",
                R.drawable.ic_launcher_background
            ),
            ClassNotifikasi(
                "3",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "12",
                R.drawable.ic_launcher_background
            ),
            ClassNotifikasi(
                "4",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "12",
                R.drawable.ic_launcher_background
            ),
            ClassNotifikasi(
                "5",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "12",
                R.drawable.ic_launcher_background
            )
        )
        val forumsAdapter = NotifikasiAdapter(notifikasi.reversed()) { notifikasi ->
            run {
                toast(notifikasi.id)
            }
        }
        root.recyclerViewNotifikasi_1.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = forumsAdapter
        }
    }*/
    /*private fun setupViewPager(viewPager: ViewPager) {
        val adapter = Adapter(childFragmentManager)
        adapter.addFragment(NotifikasiNewsFragment(), "News")
        adapter.addFragment(NotifikasiForumFragment(), "Forum")
        adapter.addFragment(NotifikasiEventFragment(), "Event")
        viewPager.adapter = adapter
    }
    internal class Adapter(manager: FragmentManager?) :
        FragmentPagerAdapter(manager!!) {
        private val mFragmentList: MutableList<Fragment> = ArrayList()
        private val mFragmentTitleList: MutableList<String> = ArrayList()
        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }*/
}