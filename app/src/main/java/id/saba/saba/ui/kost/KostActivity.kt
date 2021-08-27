package id.saba.saba.ui.kost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import id.saba.saba.data.adapters.KostAdapter
import id.saba.saba.data.models.Kost
import id.saba.saba.databinding.ActivityKostBinding
import splitties.activities.start

class KostActivity : AppCompatActivity(), OnMapReadyCallback, KostAdapter.OnKostClickListener {
    private lateinit var binding: ActivityKostBinding
    private lateinit var fm: FragmentManager
    private lateinit var map: GoogleMap
    private lateinit var kosts: ArrayList<Kost>
    private lateinit var adapter: KostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityKostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun loadData() {
        kosts.add(Kost(1, "https://picsum.photos/200/300", "Kos 123", 4.32, "Kos Laki-laki", "Universitas Ciputra, Jawa Timur, Indonesia", LatLng(-7.2874004, 112.6314373), false))
        adapter.notifyItemInserted(0)
        kosts.add(Kost(2, "https://picsum.photos/200/300", "Kos 321", 4.76, "Kos Cewe", "Universitas Ciputra, Jawa Timur, Indonesia", LatLng(-7.2890247, 112.6327868), false))
        adapter.notifyItemInserted(1)
    }

    private fun initView() {
        val mapFragment = SupportMapFragment.newInstance()
        fm = supportFragmentManager
        fm.beginTransaction()
            .add(binding.fragmentView.id, mapFragment)
            .commit()
        mapFragment.getMapAsync(this)

        BottomSheetBehavior.from(binding.sheetListKost).apply {
            peekHeight = 128
            state = BottomSheetBehavior.STATE_COLLAPSED
        }

        binding.btnBack.setOnClickListener { finish() }

        kosts = arrayListOf()
        adapter = KostAdapter(this, kosts, this)
        binding.kostRV.adapter = adapter
        binding.kostRV.layoutManager = (object : LinearLayoutManager(this, HORIZONTAL, false) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
                lp.width = (width / 2.3).toInt()
                return true
            }
        })

        // load data setelah layout
        loadData()
    }

    override fun onMapReady(map: GoogleMap) {
        this.map = map
        map.setPadding(0, 0, 0, 128)
        val builder = LatLngBounds.Builder()

        // tambah marker
        kosts.forEach {
            builder.include(it.koordinat)
            map.addMarker(MarkerOptions().position(it.koordinat).title(it.nama))
        }

        // biar map langsung zoom ke area semua marker
        val bounds = builder.build()
        val width = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels
        val padding = (height * 0.2).toInt()
        val cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding)
        map.animateCamera(cu)
    }

    override fun onItemClickListener(position: Int) {
        start<DetailKostActivity> { this.putExtra("KOST", kosts[position]) }
    }

    override fun onBookmartClickListener(position: Int) {
        kosts[position].boomarked = !kosts[position].boomarked
        adapter.notifyItemChanged(position)
    }
}