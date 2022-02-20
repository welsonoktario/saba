package id.saba.saba.ui.auth

import android.content.Context
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import androidx.core.content.ContextCompat
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import id.saba.saba.MainActivity
import id.saba.saba.R
import id.saba.saba.data.models.User
import id.saba.saba.databinding.ActivityLoginBinding
import splitties.activities.start

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPref: SharedPreferences
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        sharedPref = this.getSharedPreferences("USER", Context.MODE_PRIVATE)
        val data = sharedPref.getString("user", "")
        if (data != "") {
            val user = gson.fromJson(data, User::class.java)
            Log.d("USER", user.toString())
            start<MainActivity>()
        } else {
            setContentView(binding.root)
        }

        binding.buttonLogin1.setOnClickListener {
            val username = binding.txtLoginUsername.text.toString()
            val password = binding.txtLoginUsername.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                login(username, password)
            } else {
                if (username.isEmpty()) {
                    binding.txtLoginUsername.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.red_300))
                    binding.txtLoginUsername.requestFocus()
                } else {
                    binding.txtLoginUsername.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white))
                }

                if (password.isEmpty()) {
                    binding.txtLoginUsername.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.red_300))
                    binding.txtLoginUsername.requestFocus()
                } else {
                    binding.txtLoginPassword.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white))
                }
            }
        }

        binding.buttonRegistrasi1.setOnClickListener {
            start<RegisterActivity>()
        }
    }

    private fun login(username: String, password: String) {
        binding.loadingLayout.visibility = View.VISIBLE

        val user = User(1, username, "user1@example.com")
        val editor = sharedPref.edit()

        editor.putString("user", gson.toJson(user))
        editor.apply()

        binding.loadingLayout.visibility = View.GONE
        start<MainActivity>()

        /*val queue = Volley.newRequestQueue(this)
        val url = "http://chronostasis.gearhostpreview.com/userlogin.php"
        val stringRequest =
            object : StringRequest(Method.POST, url, Response.Listener {
                try {
                    val obj = JSONObject(it)
                    if (obj.getString("status") == "ada"){
                        val user = User(obj.getInt("id"), obj.getString("username"), obj.getString("email"))
                        val editor = sharedPref.edit()

                        editor.putString("user", gson.toJson(user))
                        editor.apply()

                        binding.loadingLayout.visibility = View.GONE
                        start<MainActivity>()
                    }
                    else if(obj.getString("status") == "kosong"){
                        toast("Username atau password salah")
                    }
                } catch (e: JSONException) {
                    toast(e.message.toString())
                }
            }, Response.ErrorListener {
                toast(it.message.toString())
            }){
                override fun getParams(): MutableMap<String, String> {
                    return HashMap<String, String>().apply {
                        put("username", username)
                        put("password", password)
                        put("role", "epreparedness")
                    }
                }
            }
        queue.add(stringRequest)*/
    }


}
