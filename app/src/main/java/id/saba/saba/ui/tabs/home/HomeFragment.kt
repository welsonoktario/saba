package id.saba.saba.ui.tabs.home

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.smarteist.autoimageslider.SliderView
import id.saba.saba.R
import id.saba.saba.SliderModal
import id.saba.saba.data.adapters.HomeTabAdapter
import id.saba.saba.data.adapters.SliderAdapter
import id.saba.saba.databinding.FragmentHomeBinding
import splitties.systemservices.windowManager

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private lateinit var viewPager: SliderView
    private lateinit var adapterSlider: SliderAdapter
    private lateinit var sliderModalArrayList: ArrayList<SliderModal>
    private lateinit var adapterTabHome: HomeTabAdapter
    private lateinit var viewPagerTab: ViewPager2
    private lateinit var tabLayout: TabLayout

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)

        _binding!!.layoutContent.layoutParams.height = metrics.heightPixels - 230

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
    }

    private fun initViews() {
        viewPagerTab = binding.homeVP
        tabLayout = binding.tabHome
        adapterTabHome = HomeTabAdapter(childFragmentManager, lifecycle)
        viewPagerTab.adapter = adapterTabHome

        TabLayoutMediator(tabLayout, viewPagerTab) { tab, position ->
            when (position) {
                0 -> tab.text = "News"
                1 -> tab.text = "Forum"
                2 -> tab.text = "Event"
            }
        }.attach()

        val inputMethodManager =
            requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        binding.parentLayout.setOnClickListener {
            binding.inputSearch.clearFocus()
            inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)
        }
    }
}