package id.saba.saba.ui.event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import id.saba.saba.data.models.Event
import id.saba.saba.data.models.User
import id.saba.saba.databinding.ActivityDetailEventBinding
import java.lang.Exception

class DetailEventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailEventBinding
    private lateinit var event: Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
    }

    private fun loadData() {
        val id = intent.getIntExtra("EVENT_ID", 0)
        event = if (id != 0) {
            Event(
                id,
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
            )
        } else {
            intent.getParcelableExtra("EVENT")!!
        }
        initView()
    }

    private fun initView() {
        BottomSheetBehavior.from(binding.sheetDetailEvent).apply {
            peekHeight = 128
            state = BottomSheetBehavior.STATE_EXPANDED
        }

        binding.imgDetailEventGambar.transitionName = "event-${event.id}"

        binding.txtDetailEventTitle.text = event.judul
        binding.txtDetailEventHeadline.text = event.headline
        binding.txtDetailEventDeskripsi.text = event.deskripsi
        binding.txtEventDetailTanggal.text = event.textTanggal()
        binding.txtEventDetailWaktu.text = event.textWaktu()
        binding.btnEventDetailEarly.text = event.textEarly()
        binding.btnEventDetailRegular.text = event.textRegular()
        binding.txtEventDetailLokasi.text = event.lokasi

        binding.btnBack.setOnClickListener { finish() }

        Picasso.get().load(event.gambar).noFade()
            .into(binding.imgDetailEventGambar)
    }
}