package id.saba.saba.ui.tabs.profile

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.saba.saba.R
import id.saba.saba.databinding.FragmentProfileBinding
import kotlinx.android.synthetic.main.dialog_profile_pic.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private lateinit var dialog: Dialog

    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDialog()
        view.imgProfilePiv.setOnClickListener {
            dialog.show()
        }
    }

    private fun initDialog() {
        dialog = Dialog(requireContext(), android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        dialog.setContentView(R.layout.dialog_profile_pic)
        dialog.btnClose.setOnClickListener {
            dialog.dismiss()
        }
    }
}