package id.saba.saba.ui.form

import android.app.AlertDialog
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import id.saba.saba.databinding.ActivityFormBinding


class FormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormBinding
    private lateinit var tipe: String
    private lateinit var backDialog: AlertDialog
    private lateinit var img: Uri

    private val launcher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) {
        img = it
        Picasso.get().load(it).into(binding.imgForm)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        initView()
    }

    private fun loadData() {
        tipe = intent.getStringExtra("FORM") ?: ""
    }

    private fun initView() {
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Hapus perubahan?")
            setMessage("Perubahan form anda akan hilang")
            setPositiveButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }
            setNegativeButton("Hapus") { _, _ -> finish() }
        }
        backDialog = dialog.create()

        binding.imgForm.setOnClickListener {
            openGallery()
        }

        binding.btnFormPost.setOnClickListener {
            Log.d("FORM", img.toString())
        }

        binding.btnBack.setOnClickListener { onBackPressed() }
    }

    private fun openGallery() {
        launcher.launch("image/*")
    }

    override fun onBackPressed() {
        if (binding.txtFormJudul.text.toString().isNotEmpty() || binding.txtFormIsi.text.toString()
                .isNotBlank()
        ) {
            backDialog.show()
        } else {
            finish()
        }
    }
}