package id.saba.saba.ui.event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.saba.saba.databinding.ActivityDetailEventBinding

class DetailEventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailEventBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun loadData() {

    }

    private fun initView() {

    }
}