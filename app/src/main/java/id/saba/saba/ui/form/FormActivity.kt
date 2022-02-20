package id.saba.saba.ui.form

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener
import id.saba.saba.R
import id.saba.saba.databinding.ActivityFormBinding
import jp.wasabeef.richeditor.RichEditor
import org.jsoup.Jsoup


class FormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormBinding
    private lateinit var tipe: String
    private lateinit var editor: RichEditor
    private lateinit var backDialog: AlertDialog

    private val launcherGambar = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) {
        editor.insertImage(it.toString(), "img")
        focusEditor()
    }

    private val launcherVideo = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) {
        editor.insertVideo(it.toString())
        focusEditor()
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

        editor = binding.editor
        editor.setPlaceholder("Isi konten...")
        editor.loadCSS("file:///android_asset/editor/style.css")

        binding.actionAlignCenter.setOnClickListener {
            toggleColor(it as ImageButton)
            editor.setAlignCenter()
        }
        binding.actionAlignLeft.setOnClickListener {
            toggleColor(it as ImageButton)
            editor.setAlignLeft()
        }
        binding.actionAlignRight.setOnClickListener {
            toggleColor(it as ImageButton)
            editor.setAlignRight()
        }
        binding.actionBlockquote.setOnClickListener {
            toggleColor(it as ImageButton)
            editor.setBlockquote()
        }
        binding.actionBold.setOnClickListener {
            toggleColor(it as ImageButton)
            editor.setBold()
        }
        binding.actionInsertBullets.setOnClickListener {
            toggleColor(it as ImageButton)
            editor.setBullets()
        }
        binding.actionInsertNumbers.setOnClickListener {
            toggleColor(it as ImageButton)
            editor.setNumbers()
        }
        binding.actionItalic.setOnClickListener {
            toggleColor(it as ImageButton)
            editor.setItalic()
        }
        binding.actionRedo.setOnClickListener {
            editor.redo()
        }
        binding.actionStrikethrough.setOnClickListener {
            toggleColor(it as ImageButton)
            editor.setStrikeThrough()
        }
        binding.actionSubscript.setOnClickListener {
            toggleColor(it as ImageButton)
            editor.setSubscript()
        }
        binding.actionSuperscript.setOnClickListener {
            toggleColor(it as ImageButton)
            editor.setSuperscript()
        }
        binding.actionUnderline.setOnClickListener {
            toggleColor(it as ImageButton)
            editor.setUnderline()
        }
        binding.actionUndo.setOnClickListener {
            editor.undo()
        }

        binding.actionImage.setOnClickListener {
             openGallery("image/*")
        }

        binding.actionVideo.setOnClickListener {
            openGallery("video/*")
        }

        binding.actionTxtColor.setOnClickListener {
            openColorPicker()
        }

        binding.btnBack.setOnClickListener {
            if (binding.txtFormJudul.text.toString().isNotEmpty() || editor.html.isNotEmpty()) {
                backDialog.show()
            } else {
                finish()
            }
        }

        binding.btnFormPost.setOnClickListener {
            val doc = Jsoup.parse(editor.html)
            val images = doc.select("img").map {
                it.attr("src")
            }
            val videos = doc.select("video").map {
                it.attr("src")
            }

            Log.d("IMAGES", images.toString())
            Log.d("VIDEOS", videos.toString())

            when (tipe) {
                "forum" -> true // post forum
                "news" -> true// post event
            }
        }
    }

    private fun toggleColor(v: ImageButton) {
        if (v.colorFilter == null) {
            v.setColorFilter(ContextCompat.getColor(this, R.color.primary), PorterDuff.Mode.SRC_IN)
        } else {
            v.clearColorFilter()
        }
    }

    private fun openGallery(type: String) {
         if (type == "image/*") {
             launcherGambar.launch(type)
         } else if (type == "video/*") {
             launcherVideo.launch(type)
         }
    }

    private fun openColorPicker() {
        val picker = ColorPickerDialog.Builder(this)
        picker.apply {
            setPreferenceName("Pilih Warna Teks")
            setPositiveButton("Pilih", ColorEnvelopeListener { envelope, _ ->
                editor.setTextColor(envelope.color)
            })
            setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
            })
        }
        picker.show()
    }

    private fun focusEditor() {
        binding.editor.requestFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    override fun onBackPressed() {
        if (binding.txtFormJudul.text.toString().isNotEmpty() || editor.html.isNotEmpty()) {
            backDialog.show()
        } else {
            finish()
        }
    }
}