package id.saba.saba.ui.profile

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.saba.saba.R
import kotlinx.android.synthetic.main.dialog_profile_pic.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {
    private lateinit var dialog: Dialog

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDialog()
        view.imgProfilePiv.setOnClickListener {
            dialog.show()
        }
    }

    private fun initDialog() {
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_profile_pic)
        dialog.btnClose.setOnClickListener {
            dialog.dismiss()
        }
    }
}