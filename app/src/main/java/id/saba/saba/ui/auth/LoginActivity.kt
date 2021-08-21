package id.saba.sabaid.ui.auth

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import id.saba.sabaid.MainActivity
import com.example.sabaid.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_login)

        val sharedPref: SharedPreferences = getSharedPreferences("USERNAME", Context.MODE_PRIVATE)
        val USERNAME = sharedPref.getString("USERNAME","")
        val NAME = sharedPref.getString("NAME","")
        val editor = sharedPref.edit()

        buttonLogin_1.setOnClickListener {
            if (textInputUsername_1.editText?.text.toString().trim { it <= ' '}.isEmpty()) {
                textInputUsername_1.editText?.error = "Username masih kosong"
                textInputUsername_1.requestFocus()
            }
            else if(textInputPassword_1.editText?.text.toString().trim { it <= ' '}.isEmpty()){
                textInputPassword_1.editText?.error = "Password masih kosong"
                textInputPassword_1.requestFocus()
            }
            else{
                val showLoadingDialog = indeterminateProgressDialog("Please wait for a while...", "Processing data")
                showLoadingDialog.setCancelable(false)
                showLoadingDialog.show()

                val handler = Handler()
                val queue = Volley.newRequestQueue(this)
                val url = "http://chronostasis.gearhostpreview.com/userlogin.php"
                val stringRequest =
                    object : StringRequest(Request.Method.POST, url, Response.Listener<String> {
                        try {
                            val obj = JSONObject(it)
                            handler.postDelayed({showLoadingDialog.dismiss()}, 0)
                            if(obj.getString("status") == "ada"){
                                editor.putString("USERNAME", obj.getString("username"))
                                editor.putString("NAME", obj.getString("nama"))
                                editor.apply()
                                startActivity<MainActivity>()
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
                            val params = HashMap<String, String>().apply {
                                put("username", textInputUsername_1.editText?.text.toString())
                                put("password", textInputPassword_1.editText?.text.toString())
                                put("role", "epreparedness")
                            }
                            return params
                        }
                    }
                queue.add(stringRequest)
            }
        }
        buttonRegistrasi_1.setOnClickListener {
            startActivity<RegisterActivity>()
        }
    }
}