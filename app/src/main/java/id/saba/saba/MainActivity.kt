package id.saba.saba

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import id.saba.saba.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import id.saba.saba.databinding.BottomSheetCategoryBinding
import id.saba.saba.ui.event.EventActivity
import id.saba.saba.ui.forum.ForumActivity
import id.saba.saba.ui.internship.InternshipActivity
import id.saba.saba.ui.kost.KostActivity
import id.saba.saba.ui.news.NewsActivity
import id.saba.saba.ui.scholarship.ScholarshipActivity
import splitties.activities.start

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navView.setupWithNavController(navController)
        binding.fabMenu.setOnClickListener { bottomSheetDialog.show() }
        initCategory()
    }

    private fun initCategory() {
        val bottomSheetBinding = BottomSheetCategoryBinding.inflate(layoutInflater)
        bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(bottomSheetBinding.root)

        bottomSheetBinding.cardForum.setOnClickListener {
            bottomSheetDialog.dismiss()
            start<ForumActivity>()
        }

        bottomSheetBinding.cardKost.setOnClickListener {
            bottomSheetDialog.dismiss()
            start<KostActivity>()
        }

        bottomSheetBinding.cardNews.setOnClickListener {
            bottomSheetDialog.dismiss()
            start<NewsActivity>()
        }

        bottomSheetBinding.cardEvents.setOnClickListener {
            bottomSheetDialog.dismiss()
            start<EventActivity>()
        }

        bottomSheetBinding.cardInternships.setOnClickListener {
            bottomSheetDialog.dismiss()
            start<InternshipActivity>()
        }

        bottomSheetBinding.cardScholarships.setOnClickListener {
            bottomSheetDialog.dismiss()
            start<ScholarshipActivity>()
        }
    }
}