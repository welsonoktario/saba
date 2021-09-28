package id.saba.saba.ui.auth

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.view.Window
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import id.saba.saba.R
import id.saba.saba.VolleyMultipartRequest
import id.saba.saba.databinding.ActivityRegisterBinding
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONException
import org.json.JSONObject
import splitties.toast.UnreliableToastApi
import splitties.toast.longToast
import splitties.toast.toast
import java.io.ByteArrayOutputStream
import java.io.IOException

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    var imageView: ImageView? = null
    var bitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // pengganti startActivityForResult()
        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.GetContent()) {
                if (it !== null) {
                    setImage(it)
                } else {

                }
            }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    100
                )
            }
        }

        binding.buttonSimpanR1.setOnClickListener {
            if (binding.textInputUsernameR1.editText?.text.toString().trim { it <= ' ' }
                    .isEmpty()) {
                binding.textInputUsernameR1.editText?.error = "Username masih kosong"
                binding.textInputUsernameR1.requestFocus()
            } else if (textInputPasswordR_1.editText?.text.toString().trim { it <= ' ' }
                    .isEmpty()) {
                textInputPasswordR_1.editText?.error = "Password masih kosong"
                textInputPasswordR_1.requestFocus()
            } else if (bitmap == null) {
                toast("Foto tidak tersedia")
            } else {
                uploadBitmap(bitmap!!)
            }
        }

        cardImgRegistrasi.setOnClickListener {
            resultLauncher.launch("image/*")
        }
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            val imageUri = data.data
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)

                imageView!!.setImageBitmap(bitmap)
                imageView!!.scaleType = ImageView.ScaleType.FIT_CENTER
                textViewPlaceholderR_1.text = ""
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }*/

    private fun setImage(data: Uri) {
        bitmap = when {
            Build.VERSION.SDK_INT > 28 -> {
                val source = ImageDecoder.createSource(contentResolver, data)
                ImageDecoder.decodeBitmap(source)
            }

            else -> MediaStore.Images.Media.getBitmap(
                this.contentResolver,
                data
            )
        }
        binding.layoutImg.background = BitmapDrawable(applicationContext.resources, bitmap)
    }

    fun getFileDataFromDrawable(bitmap: Bitmap): ByteArray? {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream)
        return byteArrayOutputStream.toByteArray()
    }

    @UnreliableToastApi
    private fun uploadBitmap(bitmap: Bitmap) {
        val volleyMultipartRequest = object : VolleyMultipartRequest(
            Request.Method.POST,
            "https://sabaid.azurewebsites.net/userregister.php",
            Response.Listener<NetworkResponse> {
                fun onResponse(response: NetworkResponse) {
                    try {
                        val obj = JSONObject(String(response.data))
                        longToast("Normal: " + obj.getString("status"))
                    } catch (e: JSONException) {
                        longToast("Error: " + e.message.toString())
                    }
                }
                onResponse(it)
            },
            Response.ErrorListener {
                toast(it.message.toString())
            }) {
            override fun getParams(): Map<String, String> {
                return HashMap<String, String>().apply {
                    put("username", textInputUsernameR_1.editText?.text.toString())
                    put("password", textInputPasswordR_1.editText?.text.toString())
                    put("role", "user")
                }
            }

            override fun getByteData(): MutableMap<String, DataPart> {
                val imagename = System.currentTimeMillis()
                return HashMap<String, DataPart>().apply {
                    put("pic", DataPart("$imagename.png", getFileDataFromDrawable(bitmap)))
                }
            }
        }
        Volley.newRequestQueue(this).add(volleyMultipartRequest)
    }
}