package id.saba.saba.ui.event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.squareup.picasso.Picasso
import id.saba.saba.data.models.Event
import id.saba.saba.databinding.ActivityDetailEventBinding

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
        event = intent.getParcelableExtra("EVENT")!!
        initView()
    }

    private fun initView() {
        BottomSheetBehavior.from(binding.sheetDetailEvent).apply {
            peekHeight = 128
            state = BottomSheetBehavior.STATE_EXPANDED
        }

        Picasso.get().load(event.gambar).into(binding.imgDetailEventGambar)
        binding.txtDetailEventTitle.text = event.title
        binding.txtDetailEventHeadline.text = event.headline
        binding.txtDetailEventDeskripsi.text = event.deskripsi

        binding.btnBack.setOnClickListener { finish() }
    }
}