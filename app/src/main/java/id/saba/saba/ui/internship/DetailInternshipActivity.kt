package id.saba.saba.ui.internship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import id.saba.saba.data.models.Internship
import id.saba.saba.databinding.ActivityDetailInternshipBinding

class DetailInternshipActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailInternshipBinding
    private lateinit var internship: Internship

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailInternshipBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        initView()
    }

    private fun loadData() {
        internship = intent.getParcelableExtra("INTERNSHIP")!!
    }

    private fun initView() {
        binding.txtDetailInternPosition.text = internship.judul
        binding.txtDetailInternCompNama.text = internship.company.nama
        binding.txtDetailInternCompLokasi.text = internship.company.lokasi
        binding.txtDetailInternTanggal.text = internship.tanggal
        binding.txtDetailInternDeskripsi.text = internship.description

        Picasso.get().load(internship.company.photo).into(binding.imgDetailInternCompLogo)
        Picasso.get().load(internship.gambar).into(binding.imgDetailInternGambar)
    }
}