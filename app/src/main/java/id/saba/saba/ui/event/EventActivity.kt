package id.saba.saba.ui.event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.saba.saba.R
import id.saba.saba.data.adapters.EventAdapter
import id.saba.saba.data.models.Event
import id.saba.saba.data.models.User
import id.saba.saba.databinding.ActivityEventBinding
import splitties.activities.start

class EventActivity : AppCompatActivity(), EventAdapter.OnEventClickListener {
    private lateinit var binding: ActivityEventBinding
    private lateinit var events: ArrayList<Event>
    private lateinit var adapter: EventAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        loadData()
    }

    private fun loadData() {
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
        adapter.notifyItemInserted(0) // pakai index dari loop

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
        adapter.notifyItemInserted(1)

        // reverse array untuk menampilkan data terbaru (dihapus kalau dari server udah diurut)
        events = events.reversed() as ArrayList<Event>
    }

    private fun initView() {
        // karena volley itu async, events dibikin array kosongan
        events = arrayListOf()
        adapter = EventAdapter(events, this)
        layoutManager = LinearLayoutManager(this)
        binding.eventRV.adapter = adapter
        binding.eventRV.layoutManager = layoutManager
        binding.eventRV.addItemDecoration(DividerItemDecoration(binding.eventRV.context, layoutManager.orientation))
    }

    override fun onEventCardClickListener(position: Int) {
        start<DetailEventActivity> { this.putExtra("EVENT", events[position]) }
    }
}