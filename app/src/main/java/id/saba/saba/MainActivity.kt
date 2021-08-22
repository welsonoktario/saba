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
import id.saba.saba.ui.event.EventActivity
import id.saba.saba.ui.forum.ForumActivity
import id.saba.saba.ui.internship.InternshipActivity
import id.saba.saba.ui.kost.KostActivity
import id.saba.saba.ui.news.NewsActivity
import id.saba.saba.ui.scholarship.ScholarshipActivity
import kotlinx.android.synthetic.main.bottom_sheet_category.*
import splitties.activities.start
import splitties.toast.toast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initCategory()

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

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
    }

    private fun initCategory() {
        bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_category)
        bottomSheetDialog.cardForum.setOnClickListener {
            start<ForumActivity>()
        }

        bottomSheetDialog.cardKost.setOnClickListener {
            start<KostActivity>()
        }

        bottomSheetDialog.cardNews.setOnClickListener {
            start<NewsActivity>()
        }

        bottomSheetDialog.cardEvents.setOnClickListener {
            start<EventActivity>()
        }

        bottomSheetDialog.cardInternships.setOnClickListener {
            start<InternshipActivity>()
        }

        bottomSheetDialog.cardScholarships.setOnClickListener {
            start<ScholarshipActivity>()
        }
    }
}