package id.saba.saba.ui.tabs.profile

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.saba.saba.R
import id.saba.saba.databinding.FragmentProfileBinding
import id.saba.saba.ui.auth.LoginActivity
import kotlinx.android.synthetic.main.dialog_profile_pic.*
import splitties.fragments.start

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private lateinit var dialog: Dialog
    private lateinit var sharedPref: SharedPreferences

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedPref = context.getSharedPreferences("SABA", Context.MODE_PRIVATE)
    }

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
        binding.imgProfilePiv.setOnClickListener {
            dialog.show()
        }

        binding.btnProfileLogout.setOnClickListener {
            sharedPref.edit().remove("user").commit()
            activity?.finish()
            start<LoginActivity>()
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