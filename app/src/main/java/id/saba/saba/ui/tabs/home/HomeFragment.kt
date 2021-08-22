package id.saba.saba.ui.tabs.home

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import id.saba.saba.*
import id.saba.saba.ui.adapters.SliderAdapter
import com.smarteist.autoimageslider.SliderView
import id.saba.saba.SliderModal
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {
    private lateinit var viewPager: SliderView
    private lateinit var adapter: SliderAdapter
    private lateinit var sliderModalArrayList: ArrayList<SliderModal>
    private lateinit var inputMethodManager: InputMethodManager

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
        inputMethodManager = requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        view.parentLayout.setOnClickListener {
            view.inputSearch.clearFocus()
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

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
        adapter.notifyDataSetChanged()
    }
}