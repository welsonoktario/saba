package id.saba.saba.ui.internship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.saba.saba.data.models.Internship
import id.saba.saba.databinding.ActivityDetailInternshipBinding

class DetailInternshipActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailInternshipBinding
    private lateinit var internship: Internship

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailInternshipBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun loadData() {
        internship = intent.getParcelableExtra("INTERNSHIP")!!
    }

    private fun initView() {
        binding
    }
}