package id.saba.saba

import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import id.saba.saba.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottom_sheet_category.*
import splitties.toast.toast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        //window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initCategory()

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        //val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_home, R.id.navigation_notifications, R.id.navigation_akun, R.id.notifikasiNewsFragment))
        //setupActionBarWithNavController(navController, appBarConfiguration)Ω

        navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_bottom_sheet -> {
                    bottomSheetDialog.show()
                    false
                }
                else -> {
                    navController.navigate(it.itemId)
                    true
                }
            }
        }

        //hideSystemUI()
    }

    private fun initCategory() {
        bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_category)
        bottomSheetDialog.cardForum.setOnClickListener {
            toast("Forum")
        }

        bottomSheetDialog.cardKost.setOnClickListener {
            toast("Kost")
        }

        bottomSheetDialog.cardNews.setOnClickListener {
            toast("News")
        }

        bottomSheetDialog.cardEvents.setOnClickListener {
            toast("Events")
        }

        bottomSheetDialog.cardInternships.setOnClickListener {
            toast("Internships")
        }

        bottomSheetDialog.cardScholarships.setOnClickListener {
            toast("Scholarships")
        }
    }

    private fun hideSystemUI() {
        val decorView: View = this.getWindow().getDecorView()
        val uiOptions = decorView.systemUiVisibility
        var newUiOptions = uiOptions
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_LOW_PROFILE
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_FULLSCREEN
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_IMMERSIVE
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.systemUiVisibility = newUiOptions
    }
}