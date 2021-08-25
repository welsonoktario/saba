package id.saba.saba.ui.kost

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import id.saba.saba.R
import id.saba.saba.data.models.Kost
import id.saba.saba.databinding.ActivityDetailKostBinding

class DetailKostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailKostBinding
    private lateinit var kost: Kost

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailKostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKost()
        initView()
    }

    private fun loadKost() {
        kost = intent.getParcelableExtra("KOST")!!
    }

    private fun initView() {
        Picasso.get().load(kost.gambar).into(binding.imgDetailKostGambar)
        binding.txtDetailKostNama.text = kost.nama
        binding.txtDetailKostRating.text = kost.rating.toString()
        binding.ratingBarDetailKost.rating = kost.rating.toFloat()
        binding.txtDetailKostKategori.text = kost.kategori
        binding.txtDetailKostLokasi.text = kost.lokasi

        if (kost.boomarked) {
            binding.btnDetailKostBookmark.setColorFilter(ContextCompat.getColor(this, R.color.primary))
        }

        binding.btnDetailKostBookmark.setOnClickListener {
            kost.boomarked = !kost.boomarked
            if (kost.boomarked) {
                binding.btnDetailKostBookmark.setColorFilter(ContextCompat.getColor(this, R.color.primary), PorterDuff.Mode.SRC_IN)
            } else {
                binding.btnDetailKostBookmark.clearColorFilter()
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}