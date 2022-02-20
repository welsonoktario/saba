package id.saba.saba.ui.scholarship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import id.saba.saba.data.adapters.ScholarshipAdapter
import id.saba.saba.data.models.Company
import id.saba.saba.data.models.Scholarship
import id.saba.saba.databinding.ActivityScholarshipBinding
import splitties.activities.start

class ScholarshipActivity : AppCompatActivity(), ScholarshipAdapter.OnScholarshipClickListener {
    private lateinit var binding: ActivityScholarshipBinding
    private lateinit var scholarships: ArrayList<Scholarship>
    private lateinit var adapter: ScholarshipAdapter
    private lateinit var layoutManager: LinearLayoutManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityScholarshipBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        initView()
        loadData()
    }
    
    private fun loadData() {
        scholarships.add(
            Scholarship(
                1,
                Company(
                    1,
                    "PT. Jaya Abadi",
                    "https://ui-avatars.com/api/?name=Jaya+Abadi",
                    "Surabaya, Jawa Timur, Indonesia",
                    "+6281234567890"
                ),
                "https://picsum.photos/200/300",
                "Scholarship 2",
                "Ringkasan scholarhip 2",
                "<h1>Content</h1>"
            )
        )
        adapter.notifyItemInserted(0)

        scholarships.add(
            Scholarship(
                2,
                Company(
                    1,
                    "PT. Jaya Abadi",
                    "https://ui-avatars.com/api/?name=Jaya+Abadi",
                    "Surabaya, Jawa Timur, Indonesia",
                    "+6281234567890"
                ),
                "https://picsum.photos/200/300",
                "Scholarship 3",
                "Ringkasan scholarhip 3",
                "<h1>Content</h1>"
            )
        )
        adapter.notifyItemInserted(1)

        scholarships.add(
            Scholarship(
                3,
                Company(
                    1,
                    "PT. Jaya Abadi",
                    "https://ui-avatars.com/api/?name=Jaya+Abadi",
                    "Surabaya, Jawa Timur, Indonesia",
                    "+6281234567890"
                ),
                "https://picsum.photos/200/300",
                "Scholarship 3",
                "Ringkasan scholarhip 3",
                "<h1>Content</h1>"
            )
        )
        adapter.notifyItemInserted(2)
    }

    private fun initView() {
        scholarships = arrayListOf()
        adapter = ScholarshipAdapter(scholarships, this)
        layoutManager = LinearLayoutManager(this)
        binding.scholarshipRV.adapter = adapter
        binding.scholarshipRV.layoutManager = layoutManager

        binding.btnBack.setOnClickListener { finish() }
    }

    override fun onScholarshipCardClickListener(position: Int) {
        start<DetailScholarshipActivity> { this.putExtra("SCHOLARSHIP", scholarships[position]) }
    }
}